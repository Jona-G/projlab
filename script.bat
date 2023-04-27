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


::Test17 - Szabotőr csövet lyukaszt ki
@echo off
java application/Program norandom < tests/test17_szabotor_csovet_lyukaszt/test17_input_szabotor_csovet_lyukaszt.txt > tests/test17_szabotor_csovet_lyukaszt/test17_output_szabotor_csovet_lyukaszt.txt
set count_receivePuncture=0
set count_pipe2isDamagedTrue=0
for /f "tokens=*" %%a in ('find /c /i "pipe2.receivePuncture()" tests/test17_szabotor_csovet_lyukaszt/test17_output_szabotor_csovet_lyukaszt.txt') do set count_receivePuncture=%%a
for /f "tokens=*" %%a in ('find /c /i "pipe2.isDamaged() serult" tests/test17_szabotor_csovet_lyukaszt/test17_output_szabotor_csovet_lyukaszt.txt') do set count_pipe2isDamagedTrue=%%a
if NOT "%count_receivePuncture%" == "---------- TESTS/TEST17_SZABOTOR_CSOVET_LYUKASZT/TEST17_OUTPUT_SZABOTOR_CSOVET_LYUKASZT.TXT: 2" (
	if "%count_pipe2isDamagedTrue%"=="---------- TESTS/TEST17_SZABOTOR_CSOVET_LYUKASZT/TEST17_OUTPUT_SZABOTOR_CSOVET_LYUKASZT.TXT: 2" (
		@echo on
  		echo TEST17_SZABOTOR_CSOVET_LYUKASZT Success!
	) else (
		@echo on
		echo TEST17_SZABOTOR_CSOVET_LYUKASZT Fail! pipe2.isDamaged(^) serult count is not 2.
	)
) else (
	@echo on
  	echo TEST17_SZABOTOR_CSOVET_LYUKASZT Fail! pipe2.receivePuncture(^) count is not 1.
)


::Test18 - Pumpa szívófejének átállítása (a pump0-ét pipe1-re)
@echo off
java application/Program norandom < tests/test18_pumpa_szivofej_atallitas/test18_input_pumpa_szivofej_atallitas.txt > tests/test18_pumpa_szivofej_atallitas/test18_output_pumpa_szivofej_atallitas.txt
set count_selectInputPump0Pipe1=0
for /f "tokens=*" %%a in ('find /c /i "pump0.selectInput(pipe1)" tests/test18_pumpa_szivofej_atallitas/test18_output_pumpa_szivofej_atallitas.txt') do set count_selectInputPump0Pipe1=%%a
if "%count_selectInputPump0Pipe1%"=="---------- TESTS/TEST18_PUMPA_SZIVOFEJ_ATALLITAS/TEST18_OUTPUT_PUMPA_SZIVOFEJ_ATALLITAS.TXT: 1" (
	@echo on
  	echo TEST18_PUMPA_SZIVOFEJ_ATALLITAS Success!
) else (
	@echo on
  	echo TEST18_PUMPA_SZIVOFEJ_ATALLITAS Fail! pump0.selectInput(pipe1^) count is not 1.
)


::Test19 - Pumpa kimenő fejének átállítása (a pump0-ét pipe6-ra)
@echo off
java application/Program norandom < tests/test19_pumpa_kimenofej_atallitas/test19_input_pumpa_kimenofej_atallitas.txt > tests/test19_pumpa_kimenofej_atallitas/test19_output_pumpa_kimenofej_atallitas.txt
set count_selectOutputPump0Pipe6=0
for /f "tokens=*" %%a in ('find /c /i "pump0.selectOutput(pipe6)" tests/test19_pumpa_kimenofej_atallitas/test19_output_pumpa_kimenofej_atallitas.txt') do set count_selectOutputPump0Pipe6=%%a
if "%count_selectOutputPump0Pipe6%"=="---------- TESTS/TEST19_PUMPA_KIMENOFEJ_ATALLITAS/TEST19_OUTPUT_PUMPA_KIMENOFEJ_ATALLITAS.TXT: 1" (
	@echo on
  	echo TEST19_PUMPA_KIMENOFEJ_ATALLITAS Success!
) else (
	@echo on
  	echo TEST19_PUMPA_KIMENOFEJ_ATALLITAS Fail! pump0.selectOutput(pipe6^) count is not 1.
)


::Test20 - Pumpa felvétele ciszternánál
@echo off
java application/Program norandom < tests/test20_pumpa_felvetele/test20_input_pumpa_felvetele.txt > tests/test20_pumpa_felvetele/test20_output_pumpa_felvetele.txt
set count_receivePump=0
for /f "tokens=*" %%a in ('find /c /i "repairman0.receivePump(pump4)" tests/test20_pumpa_felvetele/test20_output_pumpa_felvetele.txt') do set count_receivePump=%%a
if "%count_receivePump%"=="---------- TESTS/TEST20_PUMPA_FELVETELE/TEST20_OUTPUT_PUMPA_FELVETELE.TXT: 1" (
	@echo on
  	echo TEST20_PUMPA_FELVETELE Success!
) else (
	@echo on
  	echo TEST20_PUMPA_FELVETELE Fail! repairman0.receivePump(pump4^) count is not 1.
)


:: Test33 - Pumpa elromlik
@echo off
java application/Program 12345 < tests/test33_pumpa_elromlik/test33_input_pumpa_elromlik.txt > tests/test33_pumpa_elromlik/test33_output_pumpa_elromlik.txt
set count_pumpisdamaged=0
for /f "tokens=*" %%a in ('find /c /i "pump0.isDamaged()" tests/test33_pumpa_elromlik/test33_output_pumpa_elromlik.txt') do set count_pumpisdamaged=%%a
if "%count_pumpisdamaged%"=="---------- TESTS/TEST33_PUMPA_ELROMLIK/TEST33_OUTPUT_PUMPA_ELROMLIK.TXT: 2" (
	@echo on
  	echo TEST33_PUMPA_ELROMLIK Success!
) else (
	@echo on
  	echo TEST33_PUMPA_ELROMLIK Fail! pump0.isDamaged(^) count is not 2.
)


:: Test34 - Cső ragadóssá válik
@echo off
java application/Program norandom < tests/test34_cso_ragadossa_valik/test34_input_cso_ragadossa_valik.txt > tests/test34_cso_ragadossa_valik/test34_output_cso_ragadossa_valik.txt
set count_trytobecomesticky=0
for /f "tokens=*" %%a in ('find /c /i "pipe0.tryToBecomeSticky()" tests/test34_cso_ragadossa_valik/test34_output_cso_ragadossa_valik.txt') do set count_trytobecomesticky=%%a
if "%count_trytobecomesticky%"=="---------- TESTS/TEST34_CSO_RAGADOSSA_VALIK/TEST34_OUTPUT_CSO_RAGADOSSA_VALIK.TXT: 1" (
	@echo on
  	echo TEST34_CSO_RAGADOSSA_VALIK Success!
) else (
	@echo on
  	echo TEST34_CSO_RAGADOSSA_VALIK Fail! pipe0.tryToBecomeSticky(^) count is not 1.
)


:: Test35 - Szerelő ciszternára lép
@echo off
java application/Program norandom < tests/test35_szerelo_ciszternara_lep/test35_input_szerelo_ciszternara_lep.txt > tests/test35_szerelo_ciszternara_lep/test35_output_szerelo_ciszternara_lep.txt
set count_receivepump4=0
for /f "tokens=*" %%a in ('find /c /i "repairman0.receivePump(pump4)" tests/test35_szerelo_ciszternara_lep/test35_output_szerelo_ciszternara_lep.txt') do set count_receivepump4=%%a
if "%count_receivepump4%"=="---------- TESTS/TEST35_SZERELO_CISZTERNARA_LEP/TEST35_OUTPUT_SZERELO_CISZTERNARA_LEP.TXT: 1" (
	@echo on
  	echo TEST35_SZERELO_CISZTERNARA_LEP Success!
) else (
	@echo on
  	echo TEST35_SZERELO_CISZTERNARA_LEP Fail! repairman0.receivePump(pump4^) count is not 1.
)


:: Test36 - Csőre léphetőség
@echo off
java application/Program norandom < tests/test36_csore_lephetoseg/test36_input_csore_lephetoseg.txt > tests/test36_csore_lephetoseg/test36_output_csore_lephetoseg.txt
set count_addsaboteur0=0
set count_addrepairmean0=0
for /f "tokens=*" %%a in ('find /c /i "pipe1.addPlayer(saboteur0)" tests/test36_csore_lephetoseg/test36_output_csore_lephetoseg.txt') do set count_addsaboteur0=%%a
for /f "tokens=*" %%a in ('find /c /i "pipe1.addPlayer(repairman0)" tests/test36_csore_lephetoseg/test36_output_csore_lephetoseg.txt') do set count_addrepairman0=%%a
if "%count_addsaboteur0%"=="---------- TESTS/TEST36_CSORE_LEPHETOSEG/TEST36_OUTPUT_CSORE_LEPHETOSEG.TXT: 0" (
	if "%count_addrepairman0%"=="---------- TESTS/TEST36_CSORE_LEPHETOSEG/TEST36_OUTPUT_CSORE_LEPHETOSEG.TXT: 1" (
		@echo on
  		echo TEST36_CSORE_LEPHETOSEG Success!
	) else (
	@echo on
  	echo TEST36_CSORE_LEPHETOSEG Fail! pipe1.addPlayer(repairman0^) count is not 1.
	)
) else (
	@echo on
  	echo TEST36_CSORE_LEPHETOSEG Fail! pipe1.addPlayer(saboteur0^) count is not 0.
)