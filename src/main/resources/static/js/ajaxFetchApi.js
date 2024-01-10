// Fetch Api Ajax

function confirmUpdate() {
    var updatedSurname = document.getElementById('surnameInputUpdate').value.trim();
    var updatedFirstname = document.getElementById('firstnameInputUpdate').value.trim();

    if (updatedSurname || updatedFirstname) {
        if (selectedTeacherId) {
            // AJAX Request
            fetch('/teacherapp/updateRecord', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: 'teacherId=' + encodeURIComponent(selectedTeacherId) +
                      '&surname=' + encodeURIComponent(updatedSurname) +
                      '&firstName=' + encodeURIComponent(updatedFirstname),
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                console.log(data);
            })
            .catch(error => {
                console.error('Error:', error);
            })
            .finally(() => {
                selectedTeacherId = null;
                hideUpdateDialog();
                location.reload();
            });
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
        // AJAX Request
        fetch('/teacherapp/deleteRecord', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: 'teacherId=' + encodeURIComponent(selectedTeacherId),
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
        })
        .catch(error => {
            console.error('Error:', error);
        })
        .finally(() => {
            selectedTeacherId = null;
            hideConfirmationDialog();
            location.reload();
        });
    } else {
        alert('No teacher selected for deletion.');
    }
}


function filterTable() {
    var searchField = document.getElementById('searchField').value.trim();

    // AJAX Request
    fetch('/teacherapp/searchRecord?searchField=' + encodeURIComponent(searchField), {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        updateTable(data);
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

function updateTable(data) {
    var tbody = document.querySelector('tbody');
    tbody.innerHTML = '';

    data.forEach(function(teacherModel, index) {
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
