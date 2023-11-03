$(document).ready(function(){
    const apiUrl = 'http://localhost:8080/option/getValue';
    var token = localStorage.getItem("token");
    var header = "Bearer "+token;
    $.ajax({
        method: "GET",
        url: `${apiUrl}?option_key=thongbao`,
        headers: {
        Authorization: header,
        },
    }).done(function (msg) {
        // $("#thongbao").text(msg.data);
        $("#notice").html(msg.data);
    })
    $.ajax({
        method: "GET",
        url: `${apiUrl}?option_key=text_left_footer`,
        headers: {
        Authorization: header,
        },
    }).done(function (msg) {
        // $("#thongbao").text(msg.data);
        $("#text_left_footer").html(msg.data);
    })
    $.ajax({
        method: "GET",
        url: `${apiUrl}?option_key=text_center_footer`,
        headers: {
        Authorization: header,
        },
    }).done(function (msg) {
        // $("#thongbao").text(msg.data);
        $("#text_center_footer").html(msg.data);
    })
    $.ajax({
        method: "GET",
        url: `${apiUrl}?option_key=html_footer`,
        headers: {
        Authorization: header,
        },
    }).done(function (msg) {
        // $("#thongbao").text(msg.data);
        $("#html_footer").html(msg.data);
    })
    $.ajax({
        method: "GET",
        url: `${apiUrl}?option_key=id_video_youtube`,
        headers: {
        Authorization: header,
        },
    }).done(function (msg) {
        var html = ` <iframe src="https://youtube.com/embed/${msg.data}" frameborder="0"
        width="100%" height="350" allowfullscreen=""></iframe>`
        // $("#thongbao").text(msg.data);
        $("#optionYt").html(html);
    })
})
