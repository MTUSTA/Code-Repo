`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    19:48:23 11/30/2017 
// Design Name: 
// Module Name:    one_bit_adder 
// Project Name: 
// Target Devices: 
// Tool versions: 
// Description: 
//
// Dependencies: 
//
// Revision: 
// Revision 0.01 - File Created
// Additional Comments: 
//
//////////////////////////////////////////////////////////////////////////////////
module one_bit_adder(input A, B, Cin,output Sum,Cout);
assign Sum=A^B^Cin;
assign Cout = A&B | B&Cin | Cin&A;


endmodule
