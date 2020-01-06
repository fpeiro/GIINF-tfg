// Dom7
var $$ = Dom7;
// Framework7 App main instance
var app = new Framework7({
    root: '#app', // App root element
    id: 'io.framework7.testapp', // App bundle ID
    name: 'EVALAPP', // App name
    theme: 'ios', // Automatic theme detection
    //App translated button
    dialog: {
        buttonCancel: localStorage.getItem('cancel')
    },
    // Enable panel left visibility breakpoint
    panel: {
        leftBreakpoint: 960
    }
});

$(document).ready(function () {
    var setval = localStorage.getItem('selectCat');
    var setcat = $('[name="' + setval + '"]');
    setcat.each(function () {
        if ($(this).hasClass('modify')) {
            $(this).addClass('bg-color-aquamarine text-color-white');
        }
    });
    $('.covert').each(function () {
        if ($(this).attr('name') === setcat.attr('realval').trim()) {
            $(this).show();
        }
    });

    if (localStorage.getItem('delmsg')) {
        app.dialog.alert(localStorage.getItem('delmsg'));
        localStorage.removeItem('delmsg');
    }
    if (localStorage.getItem('addmsg')) {
        $("a[class*='swipeout-rename']").last().click();
    }
    if (localStorage.getItem('showmodel')) {
        $('#changemodel').show(100);
        localStorage.removeItem('showmodel');
    }
    if ($('#operinput').length) {
        toinput();
        updatename();
    }
});

$('.criteriorev').click(function () {
    $('.criteriorev').removeClass('bg-color-aquamarine text-color-white');
    $(this).addClass('bg-color-aquamarine text-color-white');
    var attrib = $(this).attr('name');
    $('.listarev').each(function () {
        if ($(this).attr('name') === attrib) {
            $(this).show();
        } else {
            $(this).hide();
        }
    });
});

$('#addform').click(function () {
    event.preventDefault();
    $('[name="addInput"]').attr('value', localStorage.getItem('selectCat'));
    localStorage.setItem('addmsg', 'yes');
    $('<input/>').attr({
        type: 'submit',
        name: 'add',
        hidden: 'true'
    }).appendTo('#cusform').trigger('click');
});

$('.setcat').click(function () {
    var elmnt = $(this).attr('name');
    $('#selectId').attr('value', elmnt);
    $('<input/>').attr({
        type: 'submit',
        hidden: 'true'
    }).appendTo('#cusform').trigger('click');
});

$('.setcat2').click(function () {
    var elmnt = $(this).attr('name');
    localStorage.setItem('selectCat', elmnt);
    window.location.replace("confview5");
});

$('.setev').click(function () {
    var elmnt = $(this).attr('name');
    $('#selevId').attr('value', elmnt);
    $('<input/>').attr({
        type: 'submit',
        hidden: 'true'
    }).appendTo('#cusform').trigger('click');
});

$('.btn-circle-small').click(function () {
    event.preventDefault();
    var opdor = $(this).html();
    var prev = $('#operinput').val();
    var newv = prev + ' ' + opdor;
    if ((!isNaN(prev.slice(-1)) || prev.slice(-1) === '.') && (!isNaN(opdor) || opdor === '.')) {
        newv = prev + opdor;
    }
    $('#operplace').empty();
    newv = newv.trim();
    var newa = newv.split(' ');
    for (var opndo in newa) {
        var odiv = $('<div/>').addClass('chip').appendTo('#operplace');
        $('<div/>').addClass('chip-label').html(newa[opndo]).appendTo(odiv);
        $('<a/>').attr('class', 'chip-delete').attr('chip-number', opndo).appendTo(odiv);
    }
    $('#operinput').val(newv);
    toinputh();
});


$('.op-content').click(function () {
    var opdor = $(this).find('.op-name').html().trim().split(' ').join('	');
    var prev = $('#operinput').val();
    var newv = prev + ' ' + opdor;
    $('#operplace').empty();
    newv = newv.trim();
    var newa = newv.split(' ');
    for (var opndo in newa) {
        var odiv = $('<div/>').addClass('chip').appendTo('#operplace');
        $('<div/>').addClass('chip-label').html(newa[opndo]).appendTo(odiv);
        $('<a/>').attr('class', 'chip-delete').attr('chip-number', opndo).appendTo(odiv);
    }
    $('#operinput').val(newv);
    toinputh();
});

$('.item-ucheck').click(function () {
    $('<input/>').attr({
        type: 'submit',
        hidden: 'true'
    }).appendTo('#cusform').trigger('click');
});

function toinputh() {
    var prev = $('#operinput').val().split(' ');
    var newa = [];
    for (var opndo in prev) {
        var opndox = prev[opndo].split('	').join(' ');
        if (opndox.match(/[a-z]/i)) {
            try {
                var elmnt = $(".op-name:contains('" + opndox + "')");
            } catch (err) {
            }
            if (elmnt.length) {
                opndox = elmnt.attr('op-id');
            }
        }
        newa.push(opndox);
    }
    $('#operinputh').val(newa.join(' '));
}

function toinput() {
    var prev = $('#operinputh').val().split(' ');
    var newa = [];
    for (var opndo in prev) {
        var opndox = prev[opndo];
        if (opndox.match(/[a-z]/i)) {
            try {
                var elmnt = $(".op-name[op-id=" + opndox + "]");
            } catch (err) {
            }
            if (elmnt.length) {
                opndox = elmnt.html().trim().split(' ').join('	');
            }
        }
        newa.push(opndox);
    }
    $('#operinput').val(newa.join(' '));
}

function updatename() {
    if ($('.chip').length > 0) {
        var actv = $('#operinput').val();
        $('#operplace').empty();
        var newa = actv.split(' ');
        for (var opndo in newa) {
            var odiv = $('<div/>').addClass('chip').appendTo('#operplace');
            $('<div/>').addClass('chip-label').html(newa[opndo]).appendTo(odiv);
            $('<a/>').attr('class', 'chip-delete').attr('chip-number', opndo).appendTo(odiv);
        }
        $('#operinput').val(actv);
        toinputh();
    }
}

$('#clear-btn').click(function () {
    event.preventDefault();
    $('#operplace').empty();
    $('#operinput').val('');
    $('#operinputh').val('');
});

$(document).on('click', '.chip-delete', function () {
    var cnumber = $(this).attr('chip-number');
    $(this).parent().remove();
    var ival = $('#operinput').val();
    var iarray = ival.split(' ');
    iarray.splice(cnumber, 1);
    ival = iarray.join(' ');
    $('#operinput').val(ival);
    var hval = $('#operinputh').val();
    var harray = hval.split(' ');
    harray.splice(cnumber, 1);
    hval = harray.join(' ');
    $('#operinputh').val(hval);
});

$('.prev-load').on('click', function (event) {
    var url = $(this).attr('href');
    event.preventDefault();
    app.dialog.confirm($(this).parent().attr('dialog-data'), function () {
        window.location.replace(url);
    });
});

$('.swipeout-rename').on('click', function () {
    var pthis = $(this);
    var values = [$(this).attr('cat-id')];
    if ($(this).attr('fac-id')) {
        values.push($(this).attr('fac-id'));
    }
    app.dialog.prompt($(this).attr('data-confirm'), function (value) {
        if (value === "") {
            pthis.click();
        } else {
            localStorage.removeItem('addmsg');
            values.push(value);
            $('<input/>').attr({
                type: 'hidden',
                name: 'renInput',
                value: values
            }).appendTo('#cusform');
            $('<input/>').attr({
                type: 'submit',
                name: 'rename',
                hidden: 'true'
            }).appendTo('#cusform').trigger('click');
        }
    }, function () {
        if (localStorage.getItem('addmsg')) {
            pthis.click();
        }
    });
});

$$('.swipeout-edit').on('click', function () {
    var values = [$(this).attr('cat-id'), $(this).attr('fac-id')];
    $('<input/>').attr({
        type: 'hidden',
        name: 'editInput',
        value: values
    }).appendTo('#cusform');
    $('<input/>').attr({
        type: 'submit',
        name: 'edit',
        hidden: 'true'
    }).appendTo('#cusform').trigger('click');
});

$$('.swipeout-edit2').on('click', function () {
    window.location.replace("confview9/" + $(this).attr('name'));
});

$('.swipeout-rename2').on('click', function () {
    var pthis = $(this);
    var ident = $(this).attr('crit-id');
    app.dialog.prompt($(this).attr('data-confirm'), function (value) {
        if (value === "") {
            pthis.click();
        } else {
            localStorage.removeItem('addmsg');
            $('#ren_' + ident).prop('value', value);
            $('<input/>').attr({
                type: 'submit',
                hidden: 'true'
            }).appendTo('#cusform').trigger('click');
        }
    }, function () {
        if (localStorage.getItem('addmsg')) {
            pthis.click();
        }
    });
});

$('.swipeout-rename3').on('click', function () {
    var pthis = $(this);
    var grandparent = $(this).parent().parent();
    app.dialog.prompt($(this).attr('data-confirm'), function (value) {
        if (value === "") {
            pthis.click();
        } else {
            localStorage.removeItem('addmsg');
            grandparent.find('.rm-xval').html(value);
            grandparent.find('.rm-input').val(value);
            $('#saveed').click();
        }
    }, function () {
        if (localStorage.getItem('addmsg')) {
            pthis.click();
        }
    });
});

$('.swipeout-rename4').on('click', function () {
    var pthis = $(this);
    var values = [$(this).attr('name')];
    app.dialog.prompt($(this).attr('data-confirm'), function (value) {
        if (value === "") {
            pthis.click();
        } else {
            localStorage.removeItem('addmsg');
            values.push(value);
            $('<input/>').attr({
                type: 'hidden',
                name: 'renInput',
                value: values
            }).appendTo('#cusform');
            $('<input/>').attr({
                type: 'submit',
                name: 'rename',
                hidden: 'true'
            }).appendTo('#cusform').trigger('click');
        }
    }, function () {
        if (localStorage.getItem('addmsg')) {
            pthis.click();
        }
    });
});

$('.swipeout-revalue').on('click', function () {
    var pthis = $(this);
    var grandparent = $(this).parent().parent();
    app.dialog.prompt($(this).attr('data-confirm'), function (value) {
        if (value === "") {
            pthis.click();
        } else {
            $(document).find("[name='scores[" + grandparent.attr('name') + "]']").val(value);
            $('#saveed').click();
        }
    });
});

$$('.swipeout-alert').on('click', function () {
    app.dialog.alert($(this).attr('data-alert') + "\n" + $(this).attr('name'));
});

$$('.swipeout-disable').on('click', function () {
    var index = $(this).attr('name');
    var msg = $(this).attr('data-alert');
    app.dialog.confirm($(this).attr('dialog-data'), function () {
        localStorage.setItem('delmsg', msg);
        $('<input/>').attr({
            type: 'hidden',
            name: 'disIndex',
            value: index
        }).appendTo('#cusform');
        $('<input/>').attr({
            type: 'submit',
            hidden: 'true'
        }).appendTo('#cusform').trigger('click');
    });
});

$$('.rmcrit-callback').on('swipeout:deleted', function () {
    localStorage.setItem('delmsg', $(this).attr('dialog-data'));
    var elmnt = $(this).attr('name');
    $('#removeId').attr('value', elmnt);
    $('<input/>').attr({
        type: 'submit',
        name: 'remove',
        hidden: 'true'
    }).appendTo('#cusform').trigger('click');
});

$$('.rmop-callback').on('swipeout:deleted', function () {
    localStorage.setItem('delmsg', $(this).attr('dialog-data'));
    $('<input/>').attr({
        type: 'hidden',
        name: 'removeInput',
        value: $(this).attr('name')
    }).appendTo('#cusform');
    $('<input/>').attr({
        type: 'submit',
        name: 'remove',
        hidden: 'true'
    }).appendTo('#cusform').trigger('click');
});

$$('.deleted-callback').on('swipeout:deleted', function () {
    localStorage.setItem('delmsg', $(this).attr('dialog-data'));
    var values = [$(this).attr('cat-id')];
    if ($(this).attr('fac-id')) {
        values.push($(this).attr('fac-id'));
    }
    $('<input/>').attr({
        type: 'hidden',
        name: 'removeInput',
        value: values
    }).appendTo('#cusform');
    $('<input/>').attr({
        type: 'submit',
        name: 'remove',
        hidden: 'true'
    }).appendTo('#cusform').trigger('click');
});

$('.sm-lang').on('click', function () {
    $('#changemodel').hide(100);
    $('#changelang').toggle(100);
});

$('.sm-langv').on('click', function () {
    if ($(this).attr('send-data') === 'en') {
        window.location.replace("?lang=en");
    } else if ($(this).attr('send-data') !== 'en') {
        window.location.replace("?lang=es");
    }
});

$('.sm-model').on('click', function () {
    $('#changelang').hide(100);
    $('#changemodel').toggle(100);
});

$('.sm-submit').on('click', function () {
    event.preventDefault();
    var value = $(this).attr('name');
    localStorage.setItem('showmodel', 'yes');
    $('<input/>').attr({
        type: 'hidden',
        name: 'chInput',
        value: value
    }).appendTo('#cusform');
    $('<input/>').attr({
        type: 'submit',
        hidden: 'true'
    }).appendTo('#cusform').trigger('click');
});

$('.sm-opc').on('click', function () {
    $(this).siblings().removeClass('button-active');
    var actual = $(this).parent().siblings('.sm-opcv').val().split('=');
    actual[1] = $(this).html().trim();
    $(this).parent().siblings('.sm-opcv').val(actual.join('='));
    $(this).addClass('button-active');
});


$('.sm-opc2').on('click', function () {
    $(this).siblings().removeClass('button-active');
    var actual = $(this).attr('realval');
    $(this).parent().siblings('.sm-opcv').val(actual);
    $(this).addClass('button-active');
    if (actual === 'OPTION') {
        $('.sm-opcd').removeClass('disabled');
        $('.sm-opcd').not('.maintain').css('display', '');
        $('.sm-opcd2').addClass('disabled');
        $('.sm-opcd2').css('display', 'none');
        $('.sm-opcd3').addClass('disabled');
        $('.sm-opcd3').css('display', 'none');
    } else if (actual === 'BOOLEAN') {
        $('.sm-opcd').addClass('disabled');
        $('.sm-opcd').not('.maintain').css('display', 'none');
        $('.sm-opcd2').removeClass('disabled');
        $('.sm-opcd2').css('display', '');
        $('.sm-opcd3').addClass('disabled');
        $('.sm-opcd3').css('display', 'none');
    } else {
        $('.sm-opcd').addClass('disabled');
        $('.sm-opcd').not('.maintain').css('display', 'none');
        $('.sm-opcd2').addClass('disabled');
        $('.sm-opcd2').css('display', 'none');
        $('.sm-opcd3').removeClass('disabled');
        $('.sm-opcd3').css('display', '');
    }
});

$('.sm-minus').on('click', function () {
    var decr = $(this).parent().siblings('.sm-stepv').attr('data-step');
    var max = $(this).parent().siblings('.sm-stepv').attr('data-max');
    var min = $(this).parent().siblings('.sm-stepv').attr('data-min');
    var newval = Number($(this).siblings('.sm-value').html()) - Number(decr);
    newval = Math.max(Math.min(newval, max), min);
    $(this).siblings('.sm-value').html(newval);
    $(this).parent().siblings('.sm-stepv').val(newval);
});

$('.sm-plus').on('click', function () {
    var incr = $(this).parent().siblings('.sm-stepv').attr('data-step');
    var max = $(this).parent().siblings('.sm-stepv').attr('data-max');
    var min = $(this).parent().siblings('.sm-stepv').attr('data-min');
    var newval = Number($(this).siblings('.sm-value').html()) + Number(incr);
    newval = Math.max(Math.min(newval, max), min);
    $(this).siblings('.sm-value').html(newval);
    $(this).parent().siblings('.sm-stepv').val(newval);
});

$('.swout').on('click', function () {
    var elid = $(this).attr('name');
    var elmnt = document.getElementById(elid);
    app.swipeout.open(elmnt);
});

$('[name="delete"]').on('click', function () {
    var message = $(this).attr('dialog-callback');
    app.dialog.confirm($(this).attr('dialog-data'), function () {
        localStorage.setItem('delmsg', message);
        $('<input/>').attr({
            type: 'submit',
            name: 'delete',
            hidden: 'true'
        }).appendTo('#cusform').trigger('click');
    });
});

$('.slider').on('click', function () {
    var elmnt = $(this).get(0);
    var sibling = $(this).siblings(0);
    var temppos = elmnt.value / elmnt.max;
    var outerWidth = document.getElementById('outerNode').clientWidth;
    var offset = (outerWidth - elmnt.clientWidth) / 2;
    var posLeft = (elmnt.clientWidth * temppos) + offset;
    var posTop = sibling.parent().position().top - 30;
    sibling.css({
        left: posLeft + "px",
        top: posTop + "px"
    });
    sibling.html(elmnt.value);
    sibling.fadeIn().delay(1000).fadeOut();
});

$('.logout').click(function () {
    app.dialog.confirm($(this).attr('dialog-data'), function () {
        window.location.replace("logout");
    });
});

$(document).on('click', 'input[type="checkbox"][data-group]', function () {
    var actor = $(this);
    var checked = actor.prop('checked');
    var group = actor.data('group');
    var checkboxes = $('input[type="checkbox"][data-group="' + group + '"]');
    var otherCheckboxes = checkboxes.not(actor);
    otherCheckboxes.prop('checked', checked);
});

var tonextVal = document.getElementById("tonext");
if (tonextVal) {
    tonextVal.addEventListener("click", function () {
        $('<input/>').attr({
            type: 'submit',
            name: 'tonext',
            hidden: 'true'
        }).appendTo('#cusform').trigger('click');
    });
}

var tobackVal = document.getElementById("toback");
if (tobackVal) {
    tobackVal.addEventListener("click", function () {
        $('<input/>').attr({
            type: 'submit',
            name: 'toback',
            hidden: 'true'
        }).appendTo('#cusform').trigger('click');
    });
}

var tobackVal2 = document.getElementById("toback2");
if (tobackVal2) {
    tobackVal2.addEventListener("click", function () {
        app.dialog.confirm($(this).attr('dialog-data'), function () {
            $('<input/>').attr({
                type: 'submit',
                name: 'toback',
                hidden: 'true'
            }).appendTo('#cusform').trigger('click');
        });
    });
}