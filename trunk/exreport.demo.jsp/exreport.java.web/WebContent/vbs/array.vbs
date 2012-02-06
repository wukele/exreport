	Function CreateVBArray(index)
			dim hid
			dim strcol
			dim vbaa()
			hid = "excel_data_" & index
			strcol = document.getElementById(hid).value
			'msgbox strcol
			vba = Split(strcol,",")
			l = Ubound(vba)
			redim vbaa(l,0)
			for i = 0 to l
				vbaa(i,0) = vba(i) 
			next
   			CreateVBArray = vbaa
	End Function