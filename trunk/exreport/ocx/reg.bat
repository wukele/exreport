@echo off
copy zlibwapi.dll %systemroot%\system32\
regsvr32 dsoframer.ocx /s
regsvr32 FileHelper.dll /s
regsvr32 scrrun.dll /s
regsvr32 comdlg32.ocx /s
regsvr32 mscomctl.ocx /s


echo ×é¼þ×¢²áÍê±Ï
pause