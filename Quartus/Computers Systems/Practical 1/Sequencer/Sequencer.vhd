LIBRARY ieee ;
USE ieee.std_logic_1164.all;
ENTITY Sequencer IS
	PORT
	(	clk,reset: IN STD_LOGIC;
		IR_Data : IN STD_LOGIC_VECTOR(3 downto 1);
		PC,IR_EN,ADDR_MUX,ROM,DATA_MUX,DATA_OUT,R0,R1,ADD_SUB: OUT STD_LOGIC;
		IR_D2 : OUT STD_LOGIC_VECTOR (2 downto 0)
	);
END Sequencer;

ARCHITECTURE Behavior OF Sequencer IS
	TYPE STATE_TYPE IS (Start,Set_Rom,Set_Mem,IR,MovL_Rom,MovL,Clock,Halt);
	SIGNAL state: STATE_TYPE;
BEGIN

	PROCESS (reset, clk, state)
	BEGIN
		IF reset = '1' THEN
			state <= Start;
		ELSIF clk'EVENT AND clk = '0' THEN
			
			CASE state IS
				
				WHEN Start =>
				state<=Set_Rom;
				
				WHEN Set_Rom =>
				state<=Set_Mem;
						
				WHEN Set_Mem =>
				state<=IR;

				WHEN IR =>
					
					
					IF IR_Data="000" Then
					state <= MovL_Rom;
					Elsif IR_Data="101" Then
					state<=Halt;
					Elsif IR_Data="001" or IR_Data="010" or IR_Data="011" or IR_Data="100" Then
					State<=Clock;
					End If;
					
				WHEN MovL_Rom=>
				state<=MovL;
					
				WHEN MovL=>
				state<=Clock;
				
				WHEN Clock=>
				state<=Set_Rom;
				
				WHEN Halt =>	

			END CASE;
		END IF;
	END PROCESS;

	pc <= '1' when state = Clock else '0';
	IR_EN <= '1' when state = IR else '0';
	ADDR_MUX <= '1' when state = MovL or state = MovL_Rom else '0';
	Rom <='1' when state = Set_Mem or state = Set_Rom else
		'1' when state = MovL or state = MovL_Rom  else '0';
	DATA_MUX <='1' when state = Clock and (IR_Data="010" Or IR_Data="011") else '0';
	DATA_OUT <='1' when state = Clock and IR_Data="100" else '0';
	R0 <= '1' when state = Clock and (IR_Data="000" Or IR_Data="010" Or IR_Data="011") else '0';
	R1 <= '1' when state = Clock and (IR_Data="001") else '0';
	ADD_SUB <= '1' when state = Clock and IR_Data="010" else
				 '0' when state = Clock and (IR_Data="011");
	IR_D2 <= "111" when state = Start or state = Set_Mem else IR_Data;
END Behavior;
