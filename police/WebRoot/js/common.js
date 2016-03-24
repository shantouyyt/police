

$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

function getUrlVar(name){
	var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for (var i = 0; i < hashes.length; i++) {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars[name];
}

function Json2Form(jsonData) {
    for (var o in jsonData) {
        var $t = $("#" + o);
        if ($t != undefined) {
            var $e = $t[0];
            var value = jsonData[o];

            if ($e != undefined) {
                if ($e.tagName == 'INPUT')
                    $t.val(value);
                else if ($e.tagName == 'LABEL')
                    $t.html(value);
                else if ($e.tagName == 'TEXTAREA')
                    $t.text(value);
                else if ($e.tagName == 'SELECT')
                    //$t.attr('value', value);
                	$t.val(value);
                else if ($e.tagName == 'IMG')
                    $t.attr('src', value);
            }
        }
    }
}




