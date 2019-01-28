LIBRARY ieee ;
USE ieee.std_logic_1164.all;
ENTITY Sequencer IS
	PORT
	(	clk,reset: IN	STD_LOGIC;
		IR_Data : IN	STD_LOGIC_VECTOR(3 downto 1);
		PC,IR_EN,ADDR_MUX,ROM,DATA_MUX,DATA_OUT,R0,R1,ADD_SUB: OUT STD_LOGIC;
		IR_D2 : OUT STD_LOGIC_VECTOR (2 downto 0)
	);
END Sequencer;

ARCHITECTURE Behavior OF Sequencer IS
	TYPE STATE_TYPE IS (Set_Mem,IR,MovL1,MovL2,MovR,Add,Sub,Print,Halt);
	SIGNAL state: STATE_TYPE;
BEGIN
	PROCESS (reset, clk,IR_Data)
	BEGIN
		IF reset = '1' THEN
			state <= Set_Mem;
		ELSIF clk'EVENT AND clk = '0' THEN
		
			PC<='0';
			ADDR_MUX<='0';
			ROM<='0';
			DATA_MUX<='0';
			DATA_OUT<='0';
			R0<='0';
			R1<='0';
			IR_EN<='0';
		
		
			CASE state IS
				WHEN Set_Mem =>
				ADDR_MUX<='0';
				ROM<='1';
				state<=IR;

				WHEN IR =>
				IR_EN<='1';
					
					
					IR_D2<=IR_Data;
					
					IF IR_Data="000" Then
					state <= MovL1;
					End If;
					IF IR_Data="001" Then
					state <= MovR;
					End If;
					IF IR_Data="010" Then
					state <= Add;
					End If;
					IF IR_Data="011" Then
					state <= Sub;
					End If;
					IF IR_Data="100" Then
					state <= Print;
					End If;
					IF IR_Data="101" Then
					state <= Halt;
					End If;
					
				WHEN MovL1=>
				ADDR_MUX<='1';
				ROM<='1';
				state<=MovL2;
				
				WHEN MovL2 =>
				DATA_MUX<='0';
				R0<='1';	
				PC<='1';
				state<=Set_Mem;
					
				WHEN MovR =>
				R1<='1';
				PC<='1';
				state<=Set_Mem;	
				
				WHEN Add =>
				ADD_SUB<='1';
				DATA_MUX<='1';
				R0<='1';
				PC<='1';
				state<=Set_Mem;
				
				WHEN Sub =>
				ADD_SUB<='0';
				DATA_MUX<='1';
				R0<='1';
				PC<='1';
				state<=Set_Mem ;
				
				WHEN Print =>
				DATA_OUT<='1';
				PC<='1';
				state<=Set_Mem ;
				
				WHEN Halt =>	

			END CASE;
		END IF;
	END PROCESS;

	
END Behavior;
