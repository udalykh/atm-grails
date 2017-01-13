//$(document).ready(function () {
$(function () {
    var numberInFocus = null;

    $('input[type = number]').on('focus', function () {
        numberInFocus = $(this);
    });
    $('.keyboard').on('click', 'button', function (event) {
        var $this = $(this);
        var value = $this.text();
        var z = numberInFocus.val() + value;
        if (numberInFocus != null) {
            numberInFocus.val(z);
        }
    });
    $('#clear').on('click', function (event) {
        if (numberInFocus != null) {
            numberInFocus.val('');
        }
    });
    var selectorGroup1 = $("#currencylabel, #valuelabel, #numberlabel, #amountlabel");
    var selectorGroup2 = $("#currency, #value, #number, #amount");
    var selectorGroup3 = $('.keyboard, #clear');
    selectorGroup1.hide();
    selectorGroup2.slideUp();
    selectorGroup3.slideUp();
    $('select#command').on('change', function () {
        var value = $(this).val();

        switch (value) {
            case 'REMAININGS':
                selectorGroup1.hide();
                selectorGroup2.slideUp();
                selectorGroup3.slideUp();
                break;
            case 'ADD':
                $("#currencylabel, #valuelabel, #numberlabel").show();
                $("#currency, #value, #number").slideDown();
                $("#amount").slideUp();
                $("#amountlabel").hide();
                selectorGroup3.slideDown();
                break;
            case 'WITHDRAW':
                $("#currency, #amount").slideDown();
                $("#currencylabel, #amountlabel").show();
                $("#value, #number").slideUp();
                $("#valuelabel, #numberlabel").hide();
                selectorGroup3.slideDown();
                break;
            default:
                selectorGroup2.slideUp();
                selectorGroup1.hide();
                selectorGroup3.slideUp();
                break;
        }
    });
});
//});
