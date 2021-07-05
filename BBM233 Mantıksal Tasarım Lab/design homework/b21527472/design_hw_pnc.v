`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    21:24:57 01/12/2018 
// Design Name: 
// Module Name:    design_hw 
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
module FlipFlopJK(j,k,clk,q);

input j,k;
input clk;
output q;
reg q;

initial
begin q=1'b0;
q_not=1'b1;
end
always @ (posedge clk)
begin
case({j,k})
{1'b0,1'b0}:begin q=q; end
{1'b0,1'b01}:begin q=1'b0; end
{1'b1,1'b0}:begin q=1'b1; end
{1'b1,1'b1}:begin q=~q; end
endcase
end
endmodule

module Project(Input, Clock ,Boolean,A,B,C,D);
  input Input, Clock;
  output Boolean;
  output A,B,C,D;
  wire   A,B,C,D,And1_output, And2_output,Anot_D, Anot_Bnot_C, Cnot_D_B, A_Bnot_C_D;

 //4-Bit Counter:
 FlipFlopJK FF1 (Input, Input, Clock, A);
 FlipFlopJK FF2 (A, A, Clock, B);
 and And1 (And1_output, A, B);
 FlipFlopJK FF3 (And1_output, And1_output, Clock, C);
 and And2 (And2_output, A, B, C);
 FlipFlopJK FF4 (And2_output, And2_output, Clock, D);
 
 //Calculations:
 and And3 (Anot_D, ~A, D);
 and And4 (Anot_Bnot_C, A, B, C);
 and And5 (Cnot_D_B, ~C, D, B);
 and And6 (A_Bnot_C_D, A, ~B, C, D);
 or Or1 (Boolean, Anot_D, Anot_Bnot_C, Cnot_D_B, A_Bnot_C_D);

endmodule