`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   23:29:34 01/18/2018
// Design Name:   Project
// Module Name:   C:/Users/Alperen/Desktop/BBM231/verilog/Project/Test.v
// Project Name:  Project
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: Project
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module Test;

 // Inputs
 reg Input;
 reg Clock;

 // Outputs
 wire Boolean;
 wire A;
 wire B;
 wire C;
 wire D;

 // Instantiate the Unit Under Test (UUT)
 Project uut (
  .Input(Input), 
  .Clock(Clock), 
  .Boolean(Boolean), 
  .A(A), 
  .B(B), 
  .C(C), 
  .D(D)
 );

 initial begin
   // Initialize Inputs
  $monitor("INPUTS=%d , CLOCK=%d , Boolean=%d , ABCD=%d%d%d%d ",Input,Clock,Boolean,A,B,C,D);
  Input = 1;
  Clock = 0;

  // Wait 100 ns for global reset to finish
  #50;
        
  // Add stimulus here

 end
 always #30 Clock=~Clock;     

      
endmodule