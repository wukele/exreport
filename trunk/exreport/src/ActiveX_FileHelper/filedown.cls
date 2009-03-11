VERSION 1.0 CLASS
BEGIN
  MultiUse = -1  'True
  Persistable = 0  'NotPersistable
  DataBindingBehavior = 0  'vbNone
  DataSourceBehavior  = 0  'vbNone
  MTSTransactionMode  = 0  'NotAnMTSObject
END
Attribute VB_Name = "filedown"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = True
Attribute VB_PredeclaredId = False
Attribute VB_Exposed = True


Public Function GetEnvironment(ByVal VarName As String) As String
          On Error GoTo Z
          Dim ret     As String * 1024
          Dim lResult     As Long
          lResult = GetEnvironmentVariable(VarName, ret, 1024)
          If lResult Then
                  GetEnvironment = Left(ret, lResult)
                  Exit Function
          End If
Z:
          GetEnvironment = ""
  End Function


Public Function GetTempPath() As String
 GetTempPath = GetEnvironment("TEMP") & "\"
End Function
Public Function DownloadFile(ByVal downUrl As String, ByVal localpath As String) As Boolean
    
'On Error GoTo err

Dim XMLHTTP As New XMLHTTP
 
 XMLHTTP.open "GET", downUrl, False
  XMLHTTP.send
  Contents = XMLHTTP.responseBody
  Dim oStr  As New ADODB.Stream
  oStr.Mode = 3
  oStr.Type = 1
  oStr.open
  oStr.Write Contents
  oStr.SaveToFile localpath, 2
  
  DownloadFile = True
    Exit Function
err:
    MsgBox err.Description & "-文件下载失败!", vbOKOnly, "系统提示:"
    
    DownloadFile = False
End Function

