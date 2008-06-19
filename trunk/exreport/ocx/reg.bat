@echo off
copy zlib.dll %systemroot%\system32\
regsvr32 dsoframer.ocx /s
regsvr32 FileHelper.dll /s

regsvr32 scrrun.dll

echo ×é¼þ×¢²áÍê±Ï
pause