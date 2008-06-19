Attribute VB_Name = "mod1"
Declare Function GetEnvironmentVariable Lib "kernel32" Alias "GetEnvironmentVariableA" (ByVal bsName As String, ByVal buff As String, ByVal ch As Long) As Long
