:: Test1 - Szerelő nyer
@echo off
java application/Program norandom < tests/test1_szerelo_nyer/test1_input_szerelo_nyer.txt > tests/test1_szerelo_nyer/test1_output_szerelo_nyer.txt
set count_repairmanscore=0
set count_saboteurscore=0
for /f "tokens=*" %%a in ('find /c /i "Game.increaseRepairmanScore(1)" tests/test1_szerelo_nyer/test1_output_szerelo_nyer.txt') do set count_repairmanscore=%%a
for /f "tokens=*" %%a in ('find /c /i "Game.increaseSaboteurScore(1)" tests/test1_szerelo_nyer/test1_output_szerelo_nyer.txt') do set count_saboteurscore=%%a
if "%count_repairmanscore%"=="---------- TESTS/TEST1_SZERELO_NYER/TEST1_OUTPUT_SZERELO_NYER.TXT: 100" (
	if "%count_saboteurscore%"=="---------- TESTS/TEST1_SZERELO_NYER/TEST1_OUTPUT_SZERELO_NYER.TXT: 0" (
		@echo on
  		echo TEST1_SZERELO_NYER Success!
	) else (
		@echo on
		echo TEST1_SZERELO_NYER Fail! Game.increaseSaboteurScore(1^) count is not 0.
	)
) else (
	@echo on
  	echo TEST1_SZERELO_NYER Fail! Game.increaseRepairmanScore(1^) count is not 100.
)

:: Test2 - Szabotőr nyer
@echo off
java application/Program norandom < tests/test2_szabotor_nyer/test2_input_szabotor_nyer.txt > tests/test2_szabotor_nyer/test2_output_szabotor_nyer.txt
set count_repairmanscore=0
set count_saboteurscore=0
for /f "tokens=*" %%a in ('find /c /i "Game.increaseRepairmanScore(1)" tests/test2_szabotor_nyer/test2_output_szabotor_nyer.txt') do set count_repairmanscore=%%a
for /f "tokens=*" %%a in ('find /c /i "Game.increaseSaboteurScore(1)" tests/test2_szabotor_nyer/test2_output_szabotor_nyer.txt') do set count_saboteurscore=%%a
if "%count_repairmanscore%"=="---------- TESTS/TEST2_SZABOTOR_NYER/TEST2_OUTPUT_SZABOTOR_NYER.TXT: 0" (
	if "%count_saboteurscore%"=="---------- TESTS/TEST2_SZABOTOR_NYER/TEST2_OUTPUT_SZABOTOR_NYER.TXT: 100" (
		@echo on
  		echo TEST2_SZABOTOR_NYER Success!
	) else (
		@echo on
		echo TEST2_SZABOTOR_NYER Fail! Game.increaseSaboteurScore(1^) count is not 100.
	)
) else (
	@echo on
  	echo TEST2_SZABOTOR_NYER Fail! Game.increaseRepairmanScore(1^) count is not 0.
)

:: Test3 - Pumpa elromlik
@echo off
java application/Program 12345 < tests/test3_pumpa_elromlik/test3_input_pumpa_elromlik.txt > tests/test3_pumpa_elromlik/test3_output_pumpa_elromlik.txt
set count_pumpisdamaged=0
for /f "tokens=*" %%a in ('find /c /i "pump0.isDamaged()" tests/test3_pumpa_elromlik/test3_output_pumpa_elromlik.txt') do set count_pumpisdamaged=%%a
if "%count_pumpisdamaged%"=="---------- TESTS/TEST3_PUMPA_ELROMLIK/TEST3_OUTPUT_PUMPA_ELROMLIK.TXT: 2" (
	@echo on
  	echo TEST3_PUMPA_ELROMLIK Success!
) else (
	@echo on
  	echo TEST3_PUMPA_ELROMLIK Fail! pump0.isDamaged(^) count is not 2.
)

:: Test4 - Cső ragadóssá válik
@echo off
java application/Program norandom < tests/test4_cso_ragadossa_valik/test4_input_cso_ragadossa_valik.txt > tests/test4_cso_ragadossa_valik/test4_output_cso_ragadossa_valik.txt
set count_trytobecomesticky=0
for /f "tokens=*" %%a in ('find /c /i "pipe0.tryToBecomeSticky()" tests/test4_cso_ragadossa_valik/test4_output_cso_ragadossa_valik.txt') do set count_trytobecomesticky=%%a
if "%count_trytobecomesticky%"=="---------- TESTS/TEST4_CSO_RAGADOSSA_VALIK/TEST4_OUTPUT_CSO_RAGADOSSA_VALIK.TXT: 1" (
	@echo on
  	echo TEST4_CSO_RAGADOSSA_VALIK Success!
) else (
	@echo on
  	echo TEST4_CSO_RAGADOSSA_VALIK Fail! pipe0.tryToBecomeSticky(^) count is not 1.
)

:: Test5 - Szerelő ciszternára lép
@echo off
java application/Program norandom < tests/test5_szerelo_ciszternara_lep/test5_input_szerelo_ciszternara_lep.txt > tests/test5_szerelo_ciszternara_lep/test5_output_szerelo_ciszternara_lep.txt
set count_receivepump4=0
for /f "tokens=*" %%a in ('find /c /i "repairman0.receivePump(pump4)" tests/test5_szerelo_ciszternara_lep/test5_output_szerelo_ciszternara_lep.txt') do set count_receivepump4=%%a
if "%count_receivepump4%"=="---------- TESTS/TEST5_SZERELO_CISZTERNARA_LEP/TEST5_OUTPUT_SZERELO_CISZTERNARA_LEP.TXT: 1" (
	@echo on
  	echo TEST5_SZERELO_CISZTERNARA_LEP Success!
) else (
	@echo on
  	echo TEST5_SZERELO_CISZTERNARA_LEP Fail! repairman0.receivePump(pump4^) count is not 1.
)

:: Test6 - Csőre léphetőség
@echo off
java application/Program norandom < tests/test6_csore_lephetoseg/test6_input_csore_lephetoseg.txt > tests/test6_csore_lephetoseg/test6_output_csore_lephetoseg.txt
set count_addsaboteur0=0
set count_addrepairmean0=0
for /f "tokens=*" %%a in ('find /c /i "pipe1.addPlayer(saboteur0)" tests/test6_csore_lephetoseg/test6_output_csore_lephetoseg.txt') do set count_addsaboteur0=%%a
for /f "tokens=*" %%a in ('find /c /i "pipe1.addPlayer(repairman0)" tests/test6_csore_lephetoseg/test6_output_csore_lephetoseg.txt') do set count_addrepairman0=%%a
if "%count_addsaboteur0%"=="---------- TESTS/TEST6_CSORE_LEPHETOSEG/TEST6_OUTPUT_CSORE_LEPHETOSEG.TXT: 0" (
	if "%count_addrepairman0%"=="---------- TESTS/TEST6_CSORE_LEPHETOSEG/TEST6_OUTPUT_CSORE_LEPHETOSEG.TXT: 1" (
		@echo on
  		echo TEST6_CSORE_LEPHETOSEG Success!
	) else (
	@echo on
  	echo TEST6_CSORE_LEPHETOSEG Fail! pipe1.addPlayer(repairman0^) count is not 1.
	)
) else (
	@echo on
  	echo TEST6_CSORE_LEPHETOSEG Fail! pipe1.addPlayer(saboteur0^) count is not 0.
)