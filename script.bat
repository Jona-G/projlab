@echo off
setlocal EnableDelayedExpansion

:: Test1 - Szerelo nyer
java application/Program norandom < tests/test1_szerelo_nyer/test1_input_szerelo_nyer.txt > tests/test1_szerelo_nyer/test1_output_szerelo_nyer.txt
set "parancs=findstr /R /N Game\.increaseRepairmanScore(1) tests\test1_szerelo_nyer\test1_output_szerelo_nyer.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a
set "parancs=findstr /R /N Game\.increaseSaboteurScore(1) tests\test1_szerelo_nyer\test1_output_szerelo_nyer.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter2=%%a
set "parancs=findstr /R /N Repairmen.Win. tests\test1_szerelo_nyer\test1_output_szerelo_nyer.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter3=%%a
if %counter1%==100 (
	if %counter2%==0 (
		if %counter3%==1 (
			echo TEST1_SZERELO_NYER Success!
		) else (
			echo TEST1_SZERELO_NYER Fail! Remairmen Win count is not 1.
		)	
	) else (
		echo TEST1_SZERELO_NYER Fail! Game.increaseSaboteurScore(1^) count is not 0.
	)
) else (
  	echo TEST1_SZERELO_NYER Fail! Game.increaseRepairmanScore(1^) count is not 100.
)


:: Test2 - Szabotor nyer
java application/Program norandom < tests/test2_szabotor_nyer/test2_input_szabotor_nyer.txt > tests/test2_szabotor_nyer/test2_output_szabotor_nyer.txt
set "parancs=findstr /R /N Game\.increaseSaboteurScore(1) tests\test2_szabotor_nyer\test2_output_szabotor_nyer.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a
set "parancs=findstr /R /N Game\.increaseRepairmanScore(1) tests\test2_szabotor_nyer\test2_output_szabotor_nyer.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter2=%%a
set "parancs=findstr /R /N Saboteurs.Win. tests\test2_szabotor_nyer\test2_output_szabotor_nyer.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter3=%%a
if %counter1%==100 (
	if %counter2%==0 (
		if %counter3%==1 (
			echo TEST2_SZABOTOR_NYER Success!
		) else (
			echo TEST2_SZABOTOR_NYER Fail! Saboteurs Win count is not 1.
		)	
	) else (
		echo TEST1_SZERELO_NYER Fail! Game.increaseRepairmanScore(1^) count is not 0.
	)
) else (
  	echo TEST1_SZERELO_NYER Fail! Game.increaseSaboteurScore(1^) count is not 100.
)


:: Test3 - Dontetlen
java application/Program norandom < tests/test3_dontetlen/test3_input_dontetlen.txt > tests/test3_dontetlen/test3_output_dontetlen.txt
set "parancs=findstr /R /N It.is.a.draw. tests\test3_dontetlen\test3_output_dontetlen.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a
if %counter1%==1 (
	echo TEST3_DONTETLEN Success!
) else (
  	echo TEST3_DONTETLEN Fail! It is a draw count is not 1.
)


:: Test4 - Dontetlen both over hundred
java application/Program norandom < tests/test4_dontetlen_both_over_hundred/test4_input_dontetlen_both_over_hundred.txt > tests/test4_dontetlen_both_over_hundred/test4_output_dontetlen_both_over_hundred.txt
set "parancs=findstr /R /N Game\.increaseSaboteurScore(1) tests\test4_dontetlen_both_over_hundred\test4_output_dontetlen_both_over_hundred.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a
set "parancs=findstr /R /N Game\.increaseSaboteurScore(1) tests\test4_dontetlen_both_over_hundred\test4_output_dontetlen_both_over_hundred.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter2=%%a
set "parancs=findstr /R /N It.is.a.draw. tests\test4_dontetlen_both_over_hundred\test4_output_dontetlen_both_over_hundred.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter3=%%a
if %counter1%==101 (
	if %counter2%==101 (
		if %counter3%==1 (
			echo TEST4_DONTETLEN_BOTH_OVER_HUNDRED Success!
		) else (
			echo TEST4_DONTETLEN_BOTH_OVER_HUNDRED Fail! It is a draw count is not 1.
		)
	) else (
		echo TEST4_DONTETLEN_BOTH_OVER_HUNDRED Fail! Game.increaseSaboteurScore(1^) count is not 101.
	)
) else (
  	echo TEST4_DONTETLEN_BOTH_OVER_HUNDRED Fail! Game.increaseRepairmanScore(1^) count is not 101.
)


:: Test5 - Szabotor nem kap pumpat
java application/Program norandom < tests/test5_szabotor_nem_kap_pumpat/test5_input_szabotor_nem_kap_pumpat.txt > tests/test5_szabotor_nem_kap_pumpat/test5_output_szabotor_nem_kap_pumpat.txt

set "parancs=findstr /R /N saboteur[0-9]*\.receivePump(pump[0-9]*) tests\test5_szabotor_nem_kap_pumpat\test5_output_szabotor_nem_kap_pumpat.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a

if %counter1%==0 (
	echo TEST5_SZABOTOR_NEM_KAP_PUMPAT Success!
) else (
  	echo TEST5_SZABOTOR_NEM_KAP_PUMPAT Fail! saboteur*.receivePump(pump*^) count is not 0.
)


:: Test6 - Szerelo nem kap pumpat ha mar van neki
java application/Program norandom < tests/test6_szerelo_nem_kap_pumpat_ha_mar_van_neki/test6_input_szerelo_nem_kap_pumpat_ha_mar_van_neki.txt > tests/test6_szerelo_nem_kap_pumpat_ha_mar_van_neki/test6_output_szerelo_nem_kap_pumpat_ha_mar_van_neki.txt

set "parancs=findstr /R /N repairman[0-9]*\.receivePump(pump[0-9]*) tests\test6_szerelo_nem_kap_pumpat_ha_mar_van_neki\test6_output_szerelo_nem_kap_pumpat_ha_mar_van_neki.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a

if %counter1%==1 (
	echo TEST6_SZERELO_NEM_KAP_PUMPAT_HA_MAR_VAN_NEKI Success!
) else (
  	echo TEST6_SZERELO_NEM_KAP_PUMPAT_HA_MAR_VAN_NEKI Fail! repairman*.receivePump(pump*^) count is not 1.
)


:: Test17 - Szabotőr csövet lyukaszt ki
java application/Program norandom < tests/test17_szabotor_csovet_lyukaszt/test17_input_szabotor_csovet_lyukaszt.txt > tests/test17_szabotor_csovet_lyukaszt/test17_output_szabotor_csovet_lyukaszt.txt
set count_receivePuncture=0
set count_pipe2isDamagedTrue=0
for /f "tokens=*" %%a in ('find /c /i "pipe2.receivePuncture()" tests/test17_szabotor_csovet_lyukaszt/test17_output_szabotor_csovet_lyukaszt.txt') do set count_receivePuncture=%%a
for /f "tokens=*" %%a in ('find /c /i "pipe2.isDamaged() serult" tests/test17_szabotor_csovet_lyukaszt/test17_output_szabotor_csovet_lyukaszt.txt') do set count_pipe2isDamagedTrue=%%a
if NOT "%count_receivePuncture%" == "---------- TESTS/TEST17_SZABOTOR_CSOVET_LYUKASZT/TEST17_OUTPUT_SZABOTOR_CSOVET_LYUKASZT.TXT: 2" (
	if "%count_pipe2isDamagedTrue%"=="---------- TESTS/TEST17_SZABOTOR_CSOVET_LYUKASZT/TEST17_OUTPUT_SZABOTOR_CSOVET_LYUKASZT.TXT: 2" (
  		echo TEST17_SZABOTOR_CSOVET_LYUKASZT Success!
	) else (
		echo TEST17_SZABOTOR_CSOVET_LYUKASZT Fail! pipe2.isDamaged(^) serult count is not 2.
	)
) else (
  	echo TEST17_SZABOTOR_CSOVET_LYUKASZT Fail! pipe2.receivePuncture(^) count is not 1.
)


:: Test18 - Pumpa szívófejének átállítása (a pump0-ét pipe1-re)
java application/Program norandom < tests/test18_pumpa_szivofej_atallitas/test18_input_pumpa_szivofej_atallitas.txt > tests/test18_pumpa_szivofej_atallitas/test18_output_pumpa_szivofej_atallitas.txt
set count_selectInputPump0Pipe1=0
for /f "tokens=*" %%a in ('find /c /i "pump0.selectInput(pipe1)" tests/test18_pumpa_szivofej_atallitas/test18_output_pumpa_szivofej_atallitas.txt') do set count_selectInputPump0Pipe1=%%a
if "%count_selectInputPump0Pipe1%"=="---------- TESTS/TEST18_PUMPA_SZIVOFEJ_ATALLITAS/TEST18_OUTPUT_PUMPA_SZIVOFEJ_ATALLITAS.TXT: 1" (
  	echo TEST18_PUMPA_SZIVOFEJ_ATALLITAS Success!
) else (
  	echo TEST18_PUMPA_SZIVOFEJ_ATALLITAS Fail! pump0.selectInput(pipe1^) count is not 1.
)


:: Test19 - Pumpa kimenő fejének átállítása (a pump0-ét pipe6-ra)
java application/Program norandom < tests/test19_pumpa_kimenofej_atallitas/test19_input_pumpa_kimenofej_atallitas.txt > tests/test19_pumpa_kimenofej_atallitas/test19_output_pumpa_kimenofej_atallitas.txt
set count_selectOutputPump0Pipe6=0
for /f "tokens=*" %%a in ('find /c /i "pump0.selectOutput(pipe6)" tests/test19_pumpa_kimenofej_atallitas/test19_output_pumpa_kimenofej_atallitas.txt') do set count_selectOutputPump0Pipe6=%%a
if "%count_selectOutputPump0Pipe6%"=="---------- TESTS/TEST19_PUMPA_KIMENOFEJ_ATALLITAS/TEST19_OUTPUT_PUMPA_KIMENOFEJ_ATALLITAS.TXT: 1" (
  	echo TEST19_PUMPA_KIMENOFEJ_ATALLITAS Success!
) else (
  	echo TEST19_PUMPA_KIMENOFEJ_ATALLITAS Fail! pump0.selectOutput(pipe6^) count is not 1.
)


:: Test20 - Pumpa felvétele ciszternánál
java application/Program norandom < tests/test20_pumpa_felvetele/test20_input_pumpa_felvetele.txt > tests/test20_pumpa_felvetele/test20_output_pumpa_felvetele.txt
set count_receivePump=0
for /f "tokens=*" %%a in ('find /c /i "repairman0.receivePump(pump4)" tests/test20_pumpa_felvetele/test20_output_pumpa_felvetele.txt') do set count_receivePump=%%a
if "%count_receivePump%"=="---------- TESTS/TEST20_PUMPA_FELVETELE/TEST20_OUTPUT_PUMPA_FELVETELE.TXT: 1" (
  	echo TEST20_PUMPA_FELVETELE Success!
) else (
  	echo TEST20_PUMPA_FELVETELE Fail! repairman0.receivePump(pump4^) count is not 1.
)


:: Test21 - Pumpa lerakása
java application/Program norandom < tests/test21_pumpa_lerakasa/test21_input_pumpa_lerakasa.txt > tests/test21_pumpa_lerakasa/test21_output_pumpa_lerakasa.txt
set count_repairmanPlacepump=0
set count_pipeReceivePump=0
for /f "tokens=*" %%a in ('find /c /i "repairman0.placePump()"  tests/test21_pumpa_lerakasa/test21_output_pumpa_lerakasa.txt') do set count_repairmanPlacepump=%%a
for /f "tokens=*" %%a in ('find /c /i "pipe2.receivePump(pump4)"  tests/test21_pumpa_lerakasa/test21_output_pumpa_lerakasa.txt') do set count_pipeReceivePump=%%a
if "%count_repairmanPlacepump%"=="---------- TESTS/TEST21_PUMPA_LERAKASA/TEST21_OUTPUT_PUMPA_LERAKASA.TXT: 1" (
	if "%count_pipeReceivePump%"=="---------- TESTS/TEST21_PUMPA_LERAKASA/TEST21_OUTPUT_PUMPA_LERAKASA.TXT: 1" (
  		echo TEST21_PUMPA_LERAKASA Success!
	) else (
		echo TEST21_PUMPA_LERAKASA Fail! pipe2.receivePump(pump4^) count is not 1.
	)
) else (
  	echo TEST21_PUMPA_LERAKASA Fail! repairman0.placePump(^) count is not 1.
)


:: Test22 - Víz melléfolyatása csőből
java application/Program norandom < tests/test22_csobol_mellefolyat/test22_input_csobol_mellefolyat.txt > tests/test22_csobol_mellefolyat/test22_output_csobol_mellefolyat.txt
set count_goToWaste=0
for /f "tokens=*" %%a in ('find /c /i "goToWaste" tests/test22_csobol_mellefolyat/test22_output_csobol_mellefolyat.txt') do set count_goToWaste=%%a
if "%count_goToWaste%"=="---------- TESTS/TEST22_CSOBOL_MELLEFOLYAT/TEST22_OUTPUT_CSOBOL_MELLEFOLYAT.TXT: 1" (
  	echo TEST22_CSOBOL_MELLEFOLYAT Success!
) else (
  	echo TEST22_CSOBOL_MELLEFOLYAT Fail! goToWaste count is not 1.
)


:: Test23 - Víz folyatása ciszternákba
java application/Program norandom < tests/test23_ciszternaba_folyat/test23_input_ciszternaba_folyat.txt > tests/test23_ciszternaba_folyat/test23_output_ciszternaba_folyat.txt
set count_goToCistern=0
for /f "tokens=*" %%a in ('find /c /i "goToCistern" tests/test23_ciszternaba_folyat/test23_output_ciszternaba_folyat.txt') do set count_goToCistern=%%a
if "%count_goToCistern%"=="---------- TESTS/TEST23_CISZTERNABA_FOLYAT/TEST23_OUTPUT_CISZTERNABA_FOLYAT.TXT: 2" (
  	echo TEST23_CISZTERNABA_FOLYAT Success!
) else (
  	echo TEST23_CISZTERNABA_FOLYAT Fail! goToCistern count is not 2.
)


:: Test24 -Cső megjavítása
java application/Program norandom < tests/test24_cso_megjavitasa/test24_setup.txt > tests/test24_cso_megjavitasa/test24_setup_output.txt
::Az elozo sorban levo input kilyukasztja pipe0-t (a szabotőr azon álla futás befejeztekor)
java application/Program norandom < tests/test24_cso_megjavitasa/test24_input_cso_megjavitasa.txt > tests/test24_cso_megjavitasa/test24_output_cso_megjavitasa.txt
set count_repairman0Repair=0
set count_serthetetlen=0
for /f "tokens=*" %%a in ('find /c /i "repairman0.repair()" tests/test24_cso_megjavitasa/test24_output_cso_megjavitasa.txt') do set count_repairman0Repair=%%a
for /f "tokens=*" %%a in ('find /c /i "korre serthetetlenne valt ez a cso." tests/test24_cso_megjavitasa/test24_output_cso_megjavitasa.txt') do set count_serthetetlen=%%a
if "%count_repairman0Repair%"=="---------- TESTS/TEST24_CSO_MEGJAVITASA/TEST24_OUTPUT_CSO_MEGJAVITASA.TXT: 1" (
	if "%count_serthetetlen%"=="---------- TESTS/TEST24_CSO_MEGJAVITASA/TEST24_OUTPUT_CSO_MEGJAVITASA.TXT: 1" (
  		echo TEST24_CSO_MEGJAVITASA Success!
	) else (
		echo TEST24_CSO_MEGJAVITASA Fail! korre serthetetlenne valt ez a cso. count is not 1.
	)
) else (
  	echo TEST24_CSO_MEGJAVITASA Fail! repairman0.repair(^) count is not 1.
)


:: Test33 - Pumpa elromlik
java application/Program 12345 < tests/test33_pumpa_elromlik/test33_input_pumpa_elromlik.txt > tests/test33_pumpa_elromlik/test33_output_pumpa_elromlik.txt
set count_pumpisdamaged=0
for /f "tokens=*" %%a in ('find /c /i "pump0.isDamaged()" tests/test33_pumpa_elromlik/test33_output_pumpa_elromlik.txt') do set count_pumpisdamaged=%%a
if "%count_pumpisdamaged%"=="---------- TESTS/TEST33_PUMPA_ELROMLIK/TEST33_OUTPUT_PUMPA_ELROMLIK.TXT: 2" (
  	echo TEST33_PUMPA_ELROMLIK Success!
) else (
  	echo TEST33_PUMPA_ELROMLIK Fail! pump0.isDamaged(^) count is not 2.
)


:: Test34 - Cső ragadóssá válik
java application/Program norandom < tests/test34_cso_ragadossa_valik/test34_input_cso_ragadossa_valik.txt > tests/test34_cso_ragadossa_valik/test34_output_cso_ragadossa_valik.txt
set count_trytobecomesticky=0
for /f "tokens=*" %%a in ('find /c /i "pipe0.tryToBecomeSticky()" tests/test34_cso_ragadossa_valik/test34_output_cso_ragadossa_valik.txt') do set count_trytobecomesticky=%%a
if "%count_trytobecomesticky%"=="---------- TESTS/TEST34_CSO_RAGADOSSA_VALIK/TEST34_OUTPUT_CSO_RAGADOSSA_VALIK.TXT: 1" (
  	echo TEST34_CSO_RAGADOSSA_VALIK Success!
) else (
  	echo TEST34_CSO_RAGADOSSA_VALIK Fail! pipe0.tryToBecomeSticky(^) count is not 1.
)


:: Test35 - Szerelő ciszternára lép
java application/Program norandom < tests/test35_szerelo_ciszternara_lep/test35_input_szerelo_ciszternara_lep.txt > tests/test35_szerelo_ciszternara_lep/test35_output_szerelo_ciszternara_lep.txt
set count_receivepump4=0
for /f "tokens=*" %%a in ('find /c /i "repairman0.receivePump(pump4)" tests/test35_szerelo_ciszternara_lep/test35_output_szerelo_ciszternara_lep.txt') do set count_receivepump4=%%a
if "%count_receivepump4%"=="---------- TESTS/TEST35_SZERELO_CISZTERNARA_LEP/TEST35_OUTPUT_SZERELO_CISZTERNARA_LEP.TXT: 1" (
  	echo TEST35_SZERELO_CISZTERNARA_LEP Success!
) else (
  	echo TEST35_SZERELO_CISZTERNARA_LEP Fail! repairman0.receivePump(pump4^) count is not 1.
)


:: Test36 - Csőre léphetőség
java application/Program norandom < tests/test36_csore_lephetoseg/test36_input_csore_lephetoseg.txt > tests/test36_csore_lephetoseg/test36_output_csore_lephetoseg.txt
set count_addsaboteur0=0
set count_addrepairman0=0
for /f "tokens=*" %%a in ('find /c /i "pipe1.addPlayer(saboteur0)" tests/test36_csore_lephetoseg/test36_output_csore_lephetoseg.txt') do set count_addsaboteur0=%%a
for /f "tokens=*" %%a in ('find /c /i "pipe1.addPlayer(repairman0)" tests/test36_csore_lephetoseg/test36_output_csore_lephetoseg.txt') do set count_addrepairman0=%%a
if "%count_addsaboteur0%"=="---------- TESTS/TEST36_CSORE_LEPHETOSEG/TEST36_OUTPUT_CSORE_LEPHETOSEG.TXT: 0" (
	if "%count_addrepairman0%"=="---------- TESTS/TEST36_CSORE_LEPHETOSEG/TEST36_OUTPUT_CSORE_LEPHETOSEG.TXT: 1" (
  		echo TEST36_CSORE_LEPHETOSEG Success!
	) else (
  		echo TEST36_CSORE_LEPHETOSEG Fail! pipe1.addPlayer(repairman0^) count is not 1.
	)
) else (
  	echo TEST36_CSORE_LEPHETOSEG Fail! pipe1.addPlayer(saboteur0^) count is not 0.
)

:: Test37 - Pumpa megjavítása
java application/Program 12345 < tests/test37_pumpa_megjavitasa/test37_input_pumpa_megjavitasa.txt > tests/test37_pumpa_megjavitasa/test37_output_pumpa_megjavitasa.txt
set count_pump0damaged=0
set count_pump0receiverepair=0
set count_pump0notdamaged=0
for /f "tokens=*" %%a in ('find /c /i "repairman0 located at pump0 [damaged]" tests/test37_pumpa_megjavitasa/test37_output_pumpa_megjavitasa.txt') do set count_pump0damaged=%%a
for /f "tokens=*" %%a in ('find /c /i "pump0.receiveRepair()" tests/test37_pumpa_megjavitasa/test37_output_pumpa_megjavitasa.txt') do set count_pump0receiverepair=%%a
for /f "tokens=*" %%a in ('find /c /i "repairman0 located at pump0 [not damaged]" tests/test37_pumpa_megjavitasa/test37_output_pumpa_megjavitasa.txt') do set count_pump0notdamaged=%%a
if "%count_pump0damaged%"=="---------- TESTS/TEST37_PUMPA_MEGJAVITASA/TEST37_OUTPUT_PUMPA_MEGJAVITASA.TXT: 1" (
	if "%count_pump0receiverepair%"=="---------- TESTS/TEST37_PUMPA_MEGJAVITASA/TEST37_OUTPUT_PUMPA_MEGJAVITASA.TXT: 1" (
		if "%count_pump0notdamaged%"=="---------- TESTS/TEST37_PUMPA_MEGJAVITASA/TEST37_OUTPUT_PUMPA_MEGJAVITASA.TXT: 1" (
  			echo TEST37_PUMPA_MEGJAVITASA Success!
		) else (
			echo TEST37_PUMPA_MEGJAVITASA Fail! repairman located at pump0 [not damaged] count is not 1.
		)
	) else (
  		echo TEST37_PUMPA_MEGJAVITASA Fail! pump0.receiveRepair(^) count is not 1.
	)
) else (
  	echo TEST37_PUMPA_MEGJAVITASA Fail! repairman located at pump0 [damaged] count is not 1.
)