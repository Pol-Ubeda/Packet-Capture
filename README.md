# **Simple Packet Capture Project**

This project is a basic implementation of a packet capture tool using the pcap4j library in Java. It allows users to capture network packets from a selected interface and save them to a .pcap file for further analysis.

## Features
- **Network Interface Selection**: Users can select which network interface to capture packets from.

- **Promiscuous Mode**: Captures all packets seen by the interface.

- **Packet Dumping**: Saves captured packets to a .pcap file for analysis with tools like Wireshark.

- **Real-time Packet Display**: Prints captured packets to the console.

## Requirements
- **Java**: This project requires Java to run.

- **pcap4j Library**: The pcap4j library is used for packet capture functionality.

- **Administrative Privileges**: Running this program may require administrative privileges due to the nature of packet capture.

## Usage
1- Clone this repository.

2- Build the project using Maven.

3- Run the packetCapture class.

4- Select a network interface when prompted.

5- Captured packets will be saved to a file named packets.pcap in the project directory.

## Notes
- Ensure you have the necessary permissions to capture packets on your system.

- The project uses a buffer size of 65536 bytes to prevent packet truncation and a capture timeout of 50ms.

- Feel free to modify it as needed to fit your project's specific details or style!
