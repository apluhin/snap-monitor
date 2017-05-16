/**
 * Created by aleksejpluhin on 10.11.16.
 */
$(document).on("click", "#to-login", function () {
    extracted("#register", "#login")
})

function extracted(s1, s2) {
    $(s1).addClass("form-visible");
    $(s2).removeClass("form-visible");
}
$(document).on("click", "#to-register", function () {
    extracted("#login", "#register");
})

$(document).on("click", "#send-register", function () {
    var username = $("#login-register").val();
    var password = $("#password-register").val();

    $.post("register", {login: username, password: password}, function () {
        setTimeout(window.location.replace("/"), 150); //not user
    })
})

$(document).on("click", "#send-login", function () {
    var username = $("#login-login").val();
    var password = $("#password-login").val();

    $.post("/login", {login: username, password: password}, function () {
        setTimeout(window.location.replace("/"), 150); //not user
    })
})