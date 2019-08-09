var indexx = 0;

function validateForm() {
	var fname = document.myForm.fname.value;
	var lname = document.myForm.lname.value;
	var salary = document.myForm.salary.value;
	var startDate = document.myForm.startDate.value;

	var namePattern = /^[A-Z]{1}[a-z]{2,}$/;
	var salaryPattern = /^[1-9]{1}[0-9]*[.]?[0-9]{2}$/;
	var datePattern = /([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))/;

	if (fname == "" && lname == "" && salary == "") {
		alert("All fields must be filled out");
	} else if (fname == "" && lname == "") {
		alert("Both First and Last name have to be filled out!");
		return false;
	} else if (!namePattern.test(fname) && !namePattern.test(lname)) {
		alert("Both names should start with 1 upper case letter and contain only letters!");
		return false;
	} else if (!namePattern.test(fname)) {
		alert("First name should start with 1 upper case letter and contain only letters!");
		return false;
	} else if (!namePattern.test(lname)) {
		alert("Last name should start with Upper case letter and contain only letters!");
		return false;
	} else if (salary == "") {
		alert("Salary must be filled out!");
		return false;
	} else if (!salaryPattern.test(salary)) {
		alert("Salary must be only digits!");
		return false;
	} else if (!datePattern.test(startDate)) {
		alert("Start date must have the format yyyy-mm-dd");
		return false;
	}
}

function disableButtons() {
	document.getElementById("updateButton").disabled = true;
	document.getElementById("deleteButton").disabled = true;
}

function enableButtons() {
	document.getElementById("updateButton").disabled = false;
	document.getElementById("deleteButton").disabled = false;
}

function disableInsert() {
	document.getElementById("insertButton").disabled = true;
}

function enableInsert() {
	document.getElementById("insertButton").disabled = false;
}

function changeColor(tableRow, highLight) {
	if (highLight) {
		//tableRow.style.backgroundColor = 'blue';
		tableRow.classList.toggle("rowHighlight");
	} else {
		//tableRow.style.backgroundColor = 'white';
		tableRow.classList.toggle("rowHighlight");
	}
}

function clearAll() {
	disableButtons();
	document.getElementById("fnameId").value = "";
	document.getElementById("lnameId").value = "";
	document.getElementById("salaryId").value = "";
	document.getElementById("startDateId").value = "";
	index = -1;
	var table = document.getElementById("myTable");
	for (var i = 1; i < table.rows.length; i++) {
		if (table.rows[i].classList.contains("selected")) {
			table.rows[i].classList.toggle("selected");
		}
	}
	indexx = 0;
	enableInsert();
}

function selectedRow(x) {

	var table = document.getElementById("myTable");
	disableInsert();

	for (var i = 1; i < table.rows.length; i++) {
		table.rows[i].onclick = function() {
			// disableInsert();
			if (indexx !== 0) {
				table.rows[indexx].classList.toggle("selected");
			}
			// console.log(typeof indexx);
			indexx = this.rowIndex;
			this.classList.toggle("selected");
			// console.log(typeof indexx);
			enableButtons();

			document.getElementById("empId").value = this.cells[0].innerHTML;
			document.getElementById("fnameId").value = this.cells[1].innerHTML;
			document.getElementById("lnameId").value = this.cells[2].innerHTML;
			document.getElementById("salaryId").value = this.cells[3].innerHTML;
			document.getElementById("startDateId").value = this.cells[4].innerHTML;

		};
	}
}

function addRowHandlers() {
	var table = document.getElementById("myTable");

	var rows = table.getElementsByTagName("tr");
	for (i = 0; i < rows.length; i++) {
		var currentRow = table.rows[i];
		var createClickHandler = function(row) {
			return function() {
				var cell = row.getElementsByTagName("td")[0];
				var id = cell.innerHTML;
				disableInsert();
				enableButtons();
				if (indexx !== 0) {
					table.rows[indexx].classList.toggle("selected");
				}
				indexx = this.rowIndex;
				this.classList.toggle("selected");
				
				document.getElementById("empId").value = this.cells[0].innerHTML;
				document.getElementById("fnameId").value = this.cells[1].innerHTML;
				document.getElementById("lnameId").value = this.cells[2].innerHTML;
				document.getElementById("salaryId").value = this.cells[3].innerHTML;
				document.getElementById("startDateId").value = this.cells[4].innerHTML;
			};
		};

		currentRow.onclick = createClickHandler(currentRow);
	}
}

