@echo off
copy zlib.dll %systemroot%\system32\
regsvr32 dsoframer.ocx /s
regsvr32 FileHelper.dll /s

echo ���ע�����
pause