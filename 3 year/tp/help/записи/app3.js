const proceduresByDate = {};
const patientRecords = [];

// Инициализация процедур для каждого дня
function initializeProcedures() {
    const daysInMonth = 30;
    for (let i = 1; i <= daysInMonth; i++) {
        const date = `2024-12-${i.toString().padStart(2, "0")}`;
        proceduresByDate[date] = ["Массаж", "Ароматерапия", "Йога", "Физиотерапия", "Медитация"];
    }
}

// Отображение календаря
function renderCalendar() {
    const calendar = document.getElementById("calendar");
    calendar.innerHTML = "";

    const daysInMonth = 30;
    for (let i = 1; i <= daysInMonth; i++) {
        const date = `2024-12-${i.toString().padStart(2, "0")}`;
        const dayButton = document.createElement("button");
        dayButton.textContent = `День ${i}`;
        dayButton.onclick = () => selectDate(date);
        calendar.appendChild(dayButton);
    }
}

document.addEventListener('DOMContentLoaded', function() {
    const profileImage = document.getElementById('profile-image');
    const uploadInput = document.getElementById('upload-profile-image');
    const uploadButton = document.getElementById('upload-button');

    uploadButton.addEventListener('click', function() {
        uploadInput.click(); // При клике на кнопку кликаем на input type="file"
    });

    uploadInput.addEventListener('change', function(event) {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                profileImage.src = e.target.result; // Обновляем src картинки
            }
            reader.readAsDataURL(file);
        }
    });
});

// Выбор даты
function selectDate(date) {
    const procedureList = document.getElementById("procedure-list");
    const selectedDate = document.getElementById("selected-date");
    const proceduresElement = document.getElementById("procedures");

    selectedDate.textContent = date;
    proceduresElement.innerHTML = "";

    if (proceduresByDate[date] && proceduresByDate[date].length > 0) {
        proceduresByDate[date].forEach((procedure, index) => {
            const procedureItem = document.createElement("li");
            procedureItem.textContent = procedure;
            const bookButton = document.createElement("button");
            bookButton.textContent = "Записаться";
            bookButton.onclick = () => bookProcedure(date, index);
            procedureItem.appendChild(bookButton);
            proceduresElement.appendChild(procedureItem);
        });
    } else {
        proceduresElement.textContent = "Нет доступных процедур.";
    }

    procedureList.style.display = "block";
}

// Запись на процедуру
function bookProcedure(date, procedureIndex) {
    const procedure = proceduresByDate[date][procedureIndex];
    patientRecords.push({ date, procedure });

    proceduresByDate[date].splice(procedureIndex, 1);

    selectDate(date);
    renderPatientRecords();
}



// Отмена записи 
function cancelProcedure(recordIndex) { 
    const record = patientRecords[recordIndex];
    
    if (record) {
        // Возвращаем процедуру в доступные для выбранной даты
        proceduresByDate[record.date].push(record.procedure);

        // Удаляем запись из записей пациента
        patientRecords.splice(recordIndex, 1);
        renderPatientRecords();
        selectDate(record.date);
    }
}

// Отображение записей пациента
function renderPatientRecords() {
    const recordsElement = document.getElementById("records");
    recordsElement.innerHTML = "";

    patientRecords.forEach((record, index) => {
        const recordItem = document.createElement("li");
        recordItem.textContent = `${record.date}: ${record.procedure}`;

        const cancelButton = document.createElement("button");
        cancelButton.textContent = "Отменить запись";
        cancelButton.onclick = () => cancelProcedure(index); // Добавлено действие для отмены

        recordItem.appendChild(cancelButton);
        recordsElement.appendChild(recordItem);
    });
}


// Функция для возврата на главную страницу
function goToHomePage() {
    window.location.href = "../главная%20страница/point1.html"; 
}

initializeProcedures();
renderCalendar();
