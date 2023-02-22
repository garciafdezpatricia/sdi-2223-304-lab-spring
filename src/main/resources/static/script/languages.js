$(document).ready(function () { //$(document).ready() allows us to execute a function when the doc is fully loaded
    $("#languageDropdownMenuButton a").click(function (e) { // attach event handler to languageDropdownMenuButton
        e.preventDefault(); // cancel the link behaviour
        let languageSelectedText = $(this).text(); //take the text of the dropdown menu
        let languageSelectedValue = $(this).attr("value");//take the value of the value attribute
        $("#btnLanguage").text(languageSelectedText);// set the text content of the btnLanguage to the selected language
        //window.location gets the current page address .replace replaces the current URL with the one provided
        window.location.replace('?lang=' + languageSelectedValue);
        return false;

    });
});