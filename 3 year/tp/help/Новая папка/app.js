const validUsers = [
	{ username: "ivanov", password: "12345" },
	{ username: "petrov", password: "67890" }
];

document.getElementById("login-form").addEventListener("submit", function (event) {
	event.preventDefault();
	const inputs = document.querySelectorAll(".input-box input");
	const username = inputs[0].value; // Используем "Фамилия" как логин
	const password = inputs[3].value;

	const user = validUsers.find(user => user.username === username && user.password === password);
	if (user) {
		window.location.href = "../записи/website3.html";
	} else {
		alert("Неправильный логин или пароль");
	}
});

document.getElementById("register-button").addEventListener("click", function () {
	document.getElementById("login-form").style.display = "none";
	document.getElementById("register-form").style.display = "block";
});

document.getElementById("back-to-login").addEventListener("click", function () {
	document.getElementById("register-form").style.display = "none";
	document.getElementById("login-form").style.display = "block";
});

document.getElementById("register-form").addEventListener("submit", function (event) {
	event.preventDefault();
	const registerInputs = document.querySelectorAll("#register-form .input-box input");
	const newUser = {
		username: registerInputs[0].value, // "Фамилия" как логин
		password: registerInputs[4].value  // Пароль
	};

	if (!newUser.username || !newUser.password) {
		alert("Заполните все обязательные поля.");
		return;
	}

	validUsers.push(newUser);
	alert("Регистрация успешна! Теперь вы можете войти.");
	document.getElementById("register-form").style.display = "none";
	document.getElementById("login-form").style.display = "block";
});



