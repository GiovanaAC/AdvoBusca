
function validarFormulario() {
    var email = document.getElementById("e-mail").value;
    var password = document.getElementById("password").value;

    if (email == "") {
        alert("Por Favor, Digite seu Email");
        return false;
    }

    if (password == "") {
        alert("Por Favor, Digite sua senha");
        return false
    }

    return true
}