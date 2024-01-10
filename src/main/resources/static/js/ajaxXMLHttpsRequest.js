// XMLHttpRequest Ajax

function confirmUpdate() {
    var updatedSurname = document.getElementById('surnameInputUpdate').value.trim();
    var updatedFirstname = document.getElementById('firstnameInputUpdate').value.trim();

    if (updatedSurname || updatedFirstname) {
        if (selectedTeacherId) {
            var xhr = new XMLHttpRequest();
            xhr.open('POST', '/teacherapp/updateRecordXMLHttps', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4) {
                    if (xhr.status === 200) {
                        var data = JSON.parse(xhr.responseText);
                        console.log(data);
                    } else {
                        console.error('Error:', xhr.statusText);
                    }

                    selectedTeacherId = null;
                    hideUpdateDialog();
                    location.reload();
                }
            };

            var requestBody = 'teacherId=' + encodeURIComponent(selectedTeacherId) +
                              '&surname=' + encodeURIComponent(updatedSurname) +
                              '&firstName=' + encodeURIComponent(updatedFirstname);

            // AJAX Request
            xhr.send(requestBody);
        } else {
            alert('Invalid teacherId. Please try again.');
        }
    } else {
        alert('Please provide at least one field (firstname or surname) for the update.');
    }

    return false;
}


function confirmDelete() {
    if (selectedTeacherId) {
        var xhr = new XMLHttpRequest();

        xhr.open('POST', '/teacherapp/deleteRecordXMLHttps', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                selectedTeacherId = null;
                hideConfirmationDialog();

                if (xhr.status === 200) {
                    var data = JSON.parse(xhr.responseText);
                    console.log(data);
                    location.reload();
                } else {
                    console.error('Error:', xhr.statusText);
                }
            }
        };

        var requestBody = 'teacherId=' + encodeURIComponent(selectedTeacherId);

        // AJAX Request
        xhr.send(requestBody);
    } else {
        alert('No teacher selected for deletion.');
    }
}


function filterTable() {
    var searchField = document.getElementById('searchField').value.trim();

    var xhr = new XMLHttpRequest();

    xhr.open('GET', '/teacherapp/searchRecordXMLHttps?searchField=' + encodeURIComponent(searchField), true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var data = JSON.parse(xhr.responseText);
                updateTable(data);
            } else {
                console.error('Error:', xhr.statusText);
            }
        }
    };

    // AJAX Request
    xhr.send();
}

function updateTable(data) {
    var tbody = document.querySelector('tbody');
    tbody.innerHTML = '';

    data.forEach(function (teacherModel, index) {
        var row = document.createElement('tr');
        row.innerHTML = `
            <td>${index + 1}</td>
            <td>${teacherModel.id}</td>
            <td>${teacherModel.surname}</td>
            <td>${teacherModel.firstName}</td>
            <td>
                <button id="buttonUpdate" title="Edit" type="button" class="btn btn-primary mi mi-edit" data-teacher-id="${teacherModel.id}" onclick="showUpdateDialog(this)"></button>
                <button id="buttonDelete" title="Delete" type="button" class="btn btn-danger mi mi-trash" data-teacher-id="${teacherModel.id}" onclick="showConfirmationDialog(this)"></button>
            </td>`;

        tbody.appendChild(row);
    });
}

document.addEventListener('DOMContentLoaded', function() {
    var searchField = document.getElementById('searchField');
    if (searchField) {
        searchField.addEventListener('keyup', filterTable);
    }
});
