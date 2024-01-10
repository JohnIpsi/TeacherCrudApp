let selectedTeacherId;

function showAddDialog() {
    document.getElementById('surnameInput').value = '';
    document.getElementById('firstnameInput').value = '';

    document.getElementById('addDialog').style.display = 'block';
    document.getElementById('overlayAdd').style.display = 'block';
}

function hideAddDialog() {
    document.getElementById('addDialog').style.display = 'none';
    document.getElementById('overlayAdd').style.display = 'none';
}

function showUpdateDialog(updateButton) {
    selectedTeacherId = updateButton.getAttribute('data-teacher-id');
    var surname = updateButton.getAttribute('data-surname');
    var firstname = updateButton.getAttribute('data-firstname');

    document.getElementById('selectedTeacherId').value = selectedTeacherId;
    document.getElementById('surnameInputUpdate').value = surname;
    document.getElementById('firstnameInputUpdate').value = firstname;

    document.getElementById('updateDialog').style.display = 'block';
    document.getElementById('overlayUpdate').style.display = 'block';
}

function hideUpdateDialog() {
    selectedTeacherId = null;
    document.getElementById('updateDialog').style.display = 'none';
    document.getElementById('overlayUpdate').style.display = 'none';
}

function showConfirmationDialog(deleteButton) {
    selectedTeacherId = deleteButton.getAttribute('data-teacher-id');
    document.getElementById('selectedTeacherId').value = selectedTeacherId;
    document.getElementById('confirmationDialog').style.display = 'block';
    document.getElementById('overlayDelete').style.display = 'block';
}

function hideConfirmationDialog() {
    selectedTeacherId = null;
    document.getElementById('confirmationDialog').style.display = 'none';
    document.getElementById('overlayDelete').style.display = 'none';
}

function cancelDelete() {
    hideConfirmationDialog();
}

document.addEventListener('keydown', function (event) {
    if (event.key === 'Escape') {
        hideAddDialog();
        hideUpdateDialog();
        hideConfirmationDialog();
    }
});


function validateNameInput(inputId) {
    var nameInput = document.getElementById(inputId).value;

    if (!isValidName(nameInput)) {
        alert('Please enter a valid name with only characters.');
        return false;
    }

    return true;
}

function isValidName(name) {
    return /^[a-zA-Z\s]+$/.test(name);
}

function validateAddForm() {
    var surnameInput = document.getElementById('surnameInput').value;
    var firstnameInput = document.getElementById('firstnameInput').value;

    if (!validateNameInput('surnameInput') || !validateNameInput('firstnameInput')) {
        return false;
    }

    return true;
}

function validateUpdateForm() {
    var surnameInputUpdate = document.getElementById('surnameInputUpdate').value;
    var firstnameInputUpdate = document.getElementById('firstnameInputUpdate').value;

    if (!validateNameInput('surnameInputUpdate') || !validateNameInput('firstnameInputUpdate')) {
        return false;
    }

    return true;
}
