ENTITY Sequencer IS
	PORT
	(
		clk: IN	STD_LOGIC;
		reset: IN	STD_LOGIC;
		IR : IN	STD_LOGIC_VECTOR(1 TO 8);
		PC,ADD_MUX,ROM,DATA_MUX,DATAOUT,R0,R1: OUT STD_LOGIC
	);
END Sequencer;

ARCHITECTURE a OF Sequencer IS
	TYPE STATE_TYPE IS (__state_name, __state_name, __state_name);
	SIGNAL state: STATE_TYPE;
BEGIN
	PROCESS (clk, reset)
	BEGIN
		IF __reset = '1' THEN
			state <= __state_name;
		ELSIF __clk'EVENT AND __clk = '1' THEN
			CASE state IS
				WHEN __state_name =>
					IF __condition THEN
						state <= __state_name;
					END IF;

				WHEN __state_name =>
					IF __condition THEN
						state <= __state_name;
					END IF;

				WHEN __state_name =>
					IF __condition THEN
						state <= __state_name;
					END IF;

			END CASE;
		END IF;
	END PROCESS;

	WITH state SELECT
		__output_name	<=	__output_value	WHEN	__state_name,
							__output_value	WHEN	__state_name,
							__output_value	WHEN	__state_name;
END a;
