
function Oamuser_Js_getRowSelected(){
	
	$index = document.querySelector('input[name=Oamuser_InGroup_1]:checked').value;
	$row = Global_JS_GetRowSelected('Oamuser_Table_1',$index);
	document.getElementById("txtID").value = $row.cells[1].innerHTML;
	document.getElementById("txtFirstName").value = $row.cells[2].innerHTML;
	document.getElementById("txtLastName").value = $row.cells[3].innerHTML;
	document.getElementById("txtMark").value = $row.cells[4].innerHTML;
}
 