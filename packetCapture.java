package com.example;

import org.pcap4j.core.NotOpenException; //Throws exception if cant open PcapHandle
import org.pcap4j.core.PcapHandle;  //Handle for capturing packets form network interface
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;    //Network interface
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;    //Defines whether we capture all packets
import org.pcap4j.core.PcapDumper;  //To write in pcap file
import org.pcap4j.core.PacketListener;
import org.pcap4j.util.NifSelector; //Class for selecting network interface

import java.io.IOException;

public class packetCapture {
    public static void main(String[] args) throws IOException{
        //First we select network interface
        NifSelector nifSelector = new NifSelector();
        PcapNetworkInterface nif = nifSelector.selectNetworkInterface();

        //If we encounter error, error message and exit
        if(nif == null){
            System.out.println("No network interface was selected.");
            return;
        }

        //We open the capture handle and try to capture all packets (PROMISCUOUS mode)
        //We use buffer of 65536 bytes to capture without truncatiion. Also set timeout of capture to 50ms
        try (PcapHandle handle = nif.openLive(65536, PromiscuousMode.PROMISCUOUS, 50)){
            
            //Here we could add filters for the handle, like source and destinations host.

            //Create PcapDumper to write packets in pcap file. Change "packets.pcap" to path where pcap file is
            try(PcapDumper dumper = handle.dumpOpen("packets.pcap")){ 
            
                //Define listener to be used by handle on nif
                PacketListener listener = packet -> {
                
                    //Print packets captured
                    System.out.println(packet);

                    //Write packets to pcap file for further analysis on wireshark or other tools
                    try {
                        dumper.dump(packet, handle.getTimestamp());
                    } catch (NotOpenException e) {
                        System.out.println("An error occurred while writing to the pcap file: " + e.getMessage());
                    }
                };

                //Now we use the listener to capture packets using the handle. Parameter -1 indicates infinte loop
                try{
                    handle.loop(-1, listener);
                } catch (PcapNativeException | NotOpenException e){
                    System.out.println("An error occurred during packet capture: " + e.getMessage());
                }
            }
        } catch (PcapNativeException | NotOpenException | InterruptedException e){
            System.out.println("An I/O error occurred: " + e.getMessage());
        }

    }

}