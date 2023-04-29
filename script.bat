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

:: Test7 - Pumpat sikeresel larak csore
java application/Program norandom < tests/test7_pumpat_sikeresen_lerak_csore/test7_input_pumpat_sikeresen_lerak_csore.txt > tests/test7_pumpat_sikeresen_lerak_csore/test7_output_pumpat_sikeresen_lerak_csore.txt

set "parancs=findstr /R /N repairman[0-9]*\.receivePump(pump[0-9]*) tests\test7_pumpat_sikeresen_lerak_csore\test7_output_pumpat_sikeresen_lerak_csore.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a

set "parancs=findstr /R /N A.pumpa.sikeresen.lerakva tests\test7_pumpat_sikeresen_lerak_csore\test7_output_pumpat_sikeresen_lerak_csore.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter2=%%a


if %counter1%==1 (
	if %counter2%==1 (
		echo TEST7_PUMPAT_SIKERESEN_LERAK_CSORE Success!	
	) else (
		echo TEST7_PUMPAT_SIKERESEN_LERAK_CSORE Fail! A pumpa sikeresen lerakva count is not 1.
	)
) else (
  	echo TEST7_PUMPAT_SIKERESEN_LERAK_CSORE Fail! repairman*.receivePump(pump*^) count is not 1.
)

:: Test8 - Pumpat nem tud forrasra lerakni
java application/Program norandom < tests/test8_pumpat_nem_tud_forrasra_lerakni/test8_input_pumpat_nem_tud_forrasra_lerakni.txt > tests/test8_pumpat_nem_tud_forrasra_lerakni/test8_output_pumpat_nem_tud_forrasra_lerakni.txt

set "parancs=findstr /R /N source[0-9]*\.receivePump(pump[0-9]*) tests\test8_pumpat_nem_tud_forrasra_lerakni\test8_output_pumpat_nem_tud_forrasra_lerakni.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a

set "parancs=findstr /R /N A.pumpa.sikeresen.lerakva tests\test8_pumpat_nem_tud_forrasra_lerakni\test8_output_pumpat_nem_tud_forrasra_lerakni.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter2=%%a


if %counter1%==1 (
	if %counter2%==0 (
		echo TEST8_PUMPAT_NEM_TUD_FORRASRA_LERAKNI Success!	
	) else (
		echo TEST8_PUMPAT_NEM_TUD_FORRASRA_LERAKNI Fail! A pumpa sikeresen lerakva count is not 0.
	)
) else (
  	echo TEST8_PUMPAT_NEM_TUD_FORRASRA_LERAKNI Fail! source*.receivePump(pump*^) count is not 1.
)


:: Test9 - Pumpat nem tud pumpara lerakni
java application/Program norandom < tests/test9_pumpat_nem_tud_pumpara_lerakni/test9_input_pumpat_nem_tud_pumpara_lerakni.txt > tests/test9_pumpat_nem_tud_pumpara_lerakni/test9_output_pumpat_nem_tud_pumpara_lerakni.txt

set "parancs=findstr /R /N pump[0-9]*\.receivePump(pump[0-9]*) tests\test9_pumpat_nem_tud_pumpara_lerakni\test9_output_pumpat_nem_tud_pumpara_lerakni.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a

set "parancs=findstr /R /N A.pumpa.sikeresen.lerakva tests\test9_pumpat_nem_tud_pumpara_lerakni\test9_output_pumpat_nem_tud_pumpara_lerakni.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter2=%%a


if %counter1%==1 (
	if %counter2%==0 (
		echo TEST9_PUMPAT_NEM_TUD_PUMPARA_LERAKNI Success!	
	) else (
		echo TEST9_PUMPAT_NEM_TUD_PUMPARA_LERAKNI Fail! A pumpa sikeresen lerakva count is not 0.
	)
) else (
  	echo TEST9_PUMPAT_NEM_TUD_PUMPARA_LERAKNI Fail! pump*.receivePump(pump*^) count is not 1.
)


:: Test10 - Pumpat nem tud cisternara lerakni
java application/Program norandom < tests/test10_pumpat_nem_tud_cisternara_lerakni/test10_input_pumpat_nem_tud_cisternara_lerakni.txt > tests/test10_pumpat_nem_tud_cisternara_lerakni/test10_output_pumpat_nem_tud_cisternara_lerakni.txt

set "parancs=findstr /R /N cistern[0-9]*\.receivePump(pump[0-9]*) tests\test10_pumpat_nem_tud_cisternara_lerakni\test10_output_pumpat_nem_tud_cisternara_lerakni.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a

set "parancs=findstr /R /N A.pumpa.sikeresen.lerakva tests\test10_pumpat_nem_tud_cisternara_lerakni\test10_output_pumpat_nem_tud_cisternara_lerakni.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter2=%%a


if %counter1%==1 (
	if %counter2%==0 (
		echo TEST10_PUMPAT_NEM_TUD_CISTERNARA_LERAKNI Success!	
	) else (
		echo TEST10_PUMPAT_NEM_TUD_CISTERNARA_LERAKNI Fail! A pumpa sikeresen lerakva count is not 0.
	)
) else (
  	echo TEST10_PUMPAT_NEM_TUD_CISTERNARA_LERAKNI Fail! cistern*.receivePump(pump*^) count is not 1.
)


:: Test11 - Pumpat nem tud lerakni mert nincs neki
java application/Program norandom < tests/test11_pumpat_nem_tud_lerakni_mert_nincs_neki/test11_input_pumpat_nem_tud_lerakni_mert_nincs_neki.txt > tests/test11_pumpat_nem_tud_lerakni_mert_nincs_neki/test11_output_pumpat_nem_tud_lerakni_mert_nincs_neki.txt

set "parancs=findstr /R /N Nincs.nala.pumpa,.amit.le.lehetne.rakni tests\test11_pumpat_nem_tud_lerakni_mert_nincs_neki\test11_output_pumpat_nem_tud_lerakni_mert_nincs_neki.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a

set "parancs=findstr /R /N A.pumpa.sikeresen.lerakva tests\test11_pumpat_nem_tud_lerakni_mert_nincs_neki\test11_output_pumpat_nem_tud_lerakni_mert_nincs_neki.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter2=%%a


if %counter1%==1 (
	if %counter2%==0 (
		echo TEST11_PUMPAT_NEM_TUD_LERAKNI_MERT_NINCS_NEKI Success!	
	) else (
		echo TEST11_PUMPAT_NEM_TUD_LERAKNI_MERT_NINCS_NEKI Fail! A pumpa sikeresen lerakva count is not 0.
	)
) else (
  	echo TEST11_PUMPAT_NEM_TUD_LERAKNI_MERT_NINCS_NEKI Fail! Nincs nala pumpa, amit le lehetne rakni count is not 1.
)


:: Test12 - Forras vizet nyom a csobe
java application/Program norandom < tests/test12_forras_vizet_nyom_a_csobe/test12_input_forras_vizet_nyom_a_csobe.txt > tests/test12_forras_vizet_nyom_a_csobe/test12_output_forras_vizet_nyom_a_csobe.txt

set "parancs=findstr /R /N pipe0\.hasWater().nincs.vize tests\test12_forras_vizet_nyom_a_csobe\test12_output_forras_vizet_nyom_a_csobe.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a

set "parancs=findstr /R /N pipe0\.removeWater().pipe0.give.away.* tests\test12_forras_vizet_nyom_a_csobe\test12_output_forras_vizet_nyom_a_csobe.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter2=%%a

if %counter1%==1 (
	if %counter2%==1 (
		echo TEST12_FORRAS_VIZET_NYOM_A_CSOBE Success!
	) else (
  		echo TEST12_FORRAS_VIZET_NYOM_A_CSOBE Fail! 
	)
) else (
  	echo TEST12_FORRAS_VIZET_NYOM_A_CSOBE Fail!
)


:: Test13 - Forras vizet nem nyom a csobe
java application/Program norandom < tests/test13_forras_vizet_nem_nyom_a_csobe/test13_input_forras_vizet_nem_nyom_a_csobe.txt > tests/test13_forras_vizet_nem_nyom_a_csobe/test13_output_forras_vizet_nem_nyom_a_csobe.txt

set "parancs=findstr /R /N pipe0\.removeWater().pipe0.give.away.* tests\test13_forras_vizet_nem_nyom_a_csobe\test13_output_forras_vizet_nem_nyom_a_csobe.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a

if %counter1%==3 (
	echo TEST13_FORRAS_VIZET_NEM_NYOM_A_CSOBE Success!
) else (
  	echo TEST13_FORRAS_VIZET_NEM_NYOM_A_CSOBE Fail!
)


:: Test14 - Víz elfolyása kézben lévő csőből
java application/Program norandom < tests/test14_viz_elfolyasa_kezben_levo_csobol/test14_input_viz_elfolyasa_kezben_levo_csobol.txt > tests/test14_viz_elfolyasa_kezben_levo_csobol/test14_output_viz_elfolyasa_kezben_levo_csobol.txt

set "parancs=findstr /R /N pipe0\.removeWater().pipe0.give.away.* tests\test14_viz_elfolyasa_kezben_levo_csobol\test14_output_viz_elfolyasa_kezben_levo_csobol.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a

if %counter1%==4 (
	echo TEST14_VIZ_ELFOLYASA_KEZBEN_LEVO_CSOBOL Success!
) else (
  	echo TEST14_VIZ_ELFOLYASA_KEZBEN_LEVO_CSOBOL Fail!
)


:: Test15 - Csövet nem lehet felvenni, mert állnak rajta
java application/Program norandom < tests/test15_csovet_nem_lehet_felvenni_mert_allnak_rajta/test15_input_csovet_nem_lehet_felvenni_mert_allnak_rajta.txt > tests/test15_csovet_nem_lehet_felvenni_mert_allnak_rajta/test15_output_csovet_nem_lehet_felvenni_mert_allnak_rajta.txt

set "parancs=findstr /R /N Allnak.a.csovon,.nem.lehet.felvenni\. tests\test15_csovet_nem_lehet_felvenni_mert_allnak_rajta\test15_output_csovet_nem_lehet_felvenni_mert_allnak_rajta.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a

if %counter1%==1 (
	echo TEST15_CSOVET_NEM_LEHET_FELVENNI_MERT_ALLNAK_RAJTA Success!
) else (
  	echo TEST15_CSOVET_NEM_LEHET_FELVENNI_MERT_ALLNAK_RAJTA Fail!
)


:: Test16 - Szerelő csövet kilyukaszt
java application/Program norandom < tests/test16_szerelo_csovet_kilyukaszt/test16_input_szerelo_csovet_kilyukaszt.txt > tests/test16_szerelo_csovet_kilyukaszt/test16_output_szerelo_csovet_kilyukaszt.txt

set "parancs=findstr /R /N repairman0\.puncture() tests\test16_szerelo_csovet_kilyukaszt\test16_output_szerelo_csovet_kilyukaszt.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a

set "parancs=findstr /R /N pipe0\.isDamaged().serult tests\test16_szerelo_csovet_kilyukaszt\test16_output_szerelo_csovet_kilyukaszt.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter2=%%a

if %counter1%==1 (
	if %counter2%==0 (
		echo TEST16_SZERELO_CSOVET_KILYUKASZT Fail!
	) else (
  		echo TEST16_SZERELO_CSOVET_KILYUKASZT Success!
	)
) else (
  	echo TEST16_SZERELO_CSOVET_KILYUKASZT Fail!
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


:: Test21 - Pumpa lerakása betöltés után
java application/Program norandom < tests/test21_pumpa_lerakasa/test21_input_pumpa_lerakasa.txt > tests/test21_pumpa_lerakasa/test21_output_pumpa_lerakasa.txt
set count_repairmanPlacepump=0
set count_pipeReceivePump=0
for /f "tokens=*" %%a in ('find /c /i "repairman0.placePump()"  tests/test21_pumpa_lerakasa/test21_output_pumpa_lerakasa.txt') do set count_repairmanPlacepump=%%a
for /f "tokens=*" %%a in ('find /c /i "pipe2.receivePump(pump4)"  tests/test21_pumpa_lerakasa/test21_output_pumpa_lerakasa.txt') do set count_pipeReceivePump=%%a
if "%count_repairmanPlacepump%"=="---------- TESTS/TEST21_PUMPA_LERAKASA/TEST21_OUTPUT_PUMPA_LERAKASA.TXT: 1" (
	if "%count_pipeReceivePump%"=="---------- TESTS/TEST21_PUMPA_LERAKASA/TEST21_OUTPUT_PUMPA_LERAKASA.TXT: 1" (
  		echo TEST21_PUMPA_LERAKASA_BETOLTES_UTAN Success!
	) else (
		echo TEST21_PUMPA_LERAKASA_BETOLTES_UTAN Fail! pipe2.receivePump(pump4^) count is not 1.
	)
) else (
  	echo TEST21_PUMPA_LERAKASA_BETOLTES_UTAN Fail! repairman0.placePump(^) count is not 1.
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
::Az elozo sorban levo input kilyukasztja pipe0-t (a szabotőr azon áll a futás befejeztekor)
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

:: Test38 - Cső csúszóssá válik
java application/Program norandom < tests/test38_cso_csuszossa_valik/test38_input_cso_csuszossa_valik.txt > tests/test38_cso_csuszossa_valik/test38_output_cso_csuszossa_valik.txt

set "parancs=findstr /R /N pipe[0-9]*\.tryToBecomeSlippery() tests\test38_cso_csuszossa_valik\test38_output_cso_csuszossa_valik.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a

set "parancs=findstr /R /N Csuszos.lett.a.cso tests\test38_cso_csuszossa_valik\test38_output_cso_csuszossa_valik.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter2=%%a

if %counter1%==1 (
	if %counter2%==1 (
		echo TEST38_CSO_CSUSZOSSA_VALIK Success!	
	) else (
		echo TEST38_CSO_CSUSZOSSA_VALIK Fail! Csuszos lett a cso count is not 1.
	)
) else (
  	echo TEST38_CSO_CSUSZOSSA_VALIK Fail! pipe*.tryToBecomeSlippery(^) count is not 1.
)

:: Test39 - Ragadós csőre lépés
java application/Program norandom < tests/test39_ragados_csore_lepes/test39_input_ragados_csore_lepes.txt > tests/test39_ragados_csore_lepes/test39_output_ragados_csore_lepes.txt

set "parancs=findstr /R /N pipe[0-9]*\.clearSticky() tests\test39_ragados_csore_lepes\test39_output_ragados_csore_lepes.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a

set "parancs=findstr /R /N Ragacsos.csore.lepett tests\test39_ragados_csore_lepes\test39_output_ragados_csore_lepes.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter2=%%a

if %counter1%==1 (
	if %counter2%==1 (
		echo TEST39_RAGADOS_CSORE_LEPES Success!	
	) else (
		echo TEST39_RAGADOS_CSORE_LEPES Fail! Ragacsos csore lepett count is not 1.
	)
) else (
  	echo TEST39_RAGADOS_CSORE_LEPES Fail! pipe*.clearSticky(^) count is not 1.
)

:: Test40 - Csúszós csőre lépés
java application/Program norandom < tests/test40_csuszos_csore_lepes/test40_input_csuszos_csore_lepes.txt > tests/test40_csuszos_csore_lepes/test40_output_csuszos_csore_lepes.txt

set "parancs=findstr /R /N pipe[0-9]*\.tryToBecomeSlippery() tests\test40_csuszos_csore_lepes\test40_output_csuszos_csore_lepes.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a

set "parancs=findstr /R /N At.fog.csuszni.valahova tests\test40_csuszos_csore_lepes\test40_output_csuszos_csore_lepes.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter2=%%a

if %counter1%==1 (
	if %counter2%==1 (
		echo TEST40_CSUSZOS_CSORE_LEPES Success!	
	) else (
		echo TEST40_CSUSZOS_CSORE_LEPES Fail! Csuszos lett a cso count is not 1.
	)
) else (
  	echo TEST40_CSUSZOS_CSORE_LEPES Fail! pipe*.tryToBecomeSlippery(^) count is not 1.
)


:: Test41 - Két szerelő egy pumpára áll
java application/Program norandom < tests/test41_ket_szerelo_egy_pumpara_all/test41_input_ket_szerelo_egy_pumpara_all.txt > tests/test41_ket_szerelo_egy_pumpara_all/test41_output_ket_szerelo_egy_pumpara_all.txt

set "parancs=findstr /R /N repairman0.located.at.pump2 tests\test41_ket_szerelo_egy_pumpara_all\test41_output_ket_szerelo_egy_pumpara_all.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a

set "parancs=findstr /R /N repairman1.located.at.pump2 tests\test41_ket_szerelo_egy_pumpara_all\test41_output_ket_szerelo_egy_pumpara_all.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter2=%%a

if %counter1%==1 (
	if %counter2%==0 (
		echo TEST41_KET_SZERELO_EGY_PUMPARA_ALL Fail!
	) else (
  		echo TEST41_KET_SZERELO_EGY_PUMPARA_ALL Success!
	)
) else (
  	echo TEST41_KET_SZERELO_EGY_PUMPARA_ALL Fail!
)


:: Test42 - Szabotőr nem tud egy szabad végű csőre lépni
java application/Program norandom < tests/test42_szabotor_nem_tud_egy_szabad_vegu_csore_lepni/test42_input_szabotor_nem_tud_egy_szabad_vegu_csore_lepni.txt > tests/test42_szabotor_nem_tud_egy_szabad_vegu_csore_lepni/test42_output_szabotor_nem_tud_egy_szabad_vegu_csore_lepni.txt

set "parancs=findstr /R /N saboteur0.could.not.move.to.pipe. tests\test42_szabotor_nem_tud_egy_szabad_vegu_csore_lepni\test42_output_szabotor_nem_tud_egy_szabad_vegu_csore_lepni.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a

if %counter1%==1 (
	echo TEST42_SZABOTOR_NEM_TUD_EGY_SZABAD_VEGU_CSORE_LEPNI Success!
) else (
  	echo TEST42_SZABOTOR_NEM_TUD_EGY_SZABAD_VEGU_CSORE_LEPNI Fail!
)


:: Test43 - Hordozott csőre nem léphetőség
java application/Program norandom < tests/test43_hordozott_csore_nem_lephetoseg/test43_input_hordozott_csore_nem_lephetoseg.txt > tests/test43_hordozott_csore_nem_lephetoseg/test43_output_hordozott_csore_nem_lephetoseg.txt

set "parancs=findstr /R /N repairman0\.pickupPipe(pipe6) tests\test43_hordozott_csore_nem_lephetoseg\test43_output_hordozott_csore_nem_lephetoseg.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter1=%%a

set "parancs=findstr /R /N repairman1.could.not.move.to.pipe6 tests\test43_hordozott_csore_nem_lephetoseg\test43_output_hordozott_csore_nem_lephetoseg.txt | find /C ":""
for /f %%a in ('!parancs!') do set counter2=%%a

if %counter1%==1 (
	if %counter2%==1 (
		echo TEST43_HORDOZOTT_CSORE_NEM_LEPHETOSEG Success!
	) else (
  		echo TEST43_HORDOZOTT_CSORE_NEM_LEPHETOSEG Fail! repairman1 could not move to pipe 6 count is not 0.
	)
) else (
  	echo TEST43_HORDOZOTT_CSORE_NEM_LEPHETOSEG Fail! repairman0.pickupPipe(pipe6^) count is not 0.
)


@echo on
