var makeAndModelData = null;
var optionSets = null;
$(document).ready(function () {

    $.ajax({
        type: "POST",
        url: "/getMakeAndModel",
        success: function (data) {
            handleMakeAndModel(data);
            showModel($("#select-make").val());
            showInf($("#select-make").val(), $("#select-model").val());
        },
        error: function (data) {
            if (data.readyState == 0) {
                alert("Connection failure!");
                return;
            }
        },
        async: true
    });

    $("#select-make").change('shown.bs.select', function (e) {
        showModel($("#select-make").val());
        showInf($("#select-make").val(), $("#select-model").val());
    });

    $("#select-model").change('shown.bs.select', function (e) {
        showInf($("#select-make").val(), $("#select-model").val());
    });

    $("#select-make").change('shown.bs.select', function (e) {
        showModel($("#select-make").val());
    });

    $("#done-button").click(function () {
        window.location.href = `/showSelected?data=${encodeURIComponent(JSON.stringify(convertChoice()))}`;
    });

    function convertChoice() {
        var choices = [];
        for (var i = 0; i < optionSets.length; i++) {
            var choice = {
                "optionSetName": optionSets[i].name,
                "choiceName": $(`#select-optionset-${i}`).val()
            };
            choices.push(choice);
        }
        return {
            "makeName": $("#select-make").val(),
            "modelName": $("#select-model").val(),
            "choices": choices
        };
    }

    function showModel(makeName) {
        var selectOptions = "";
        for (var i = 0; i < makeAndModelData.length; i++) {
            if (makeAndModelData[i].make == makeName) {
                selectOptions += `<option>${makeAndModelData[i].model}</option>`;
            }
        }
        $("#select-model").html(selectOptions);
    }

    function handleMakeAndModel(data) {
        makeAndModelData = data;
        var selectOptions = "";
        var makes = [];
        for (var i = 0; i < makeAndModelData.length; i++) {
            var isFound = false;
            var makeNow = makeAndModelData[i].make;
            for (var j = 0; j < makes.length; j++) {
                if (makes[j] == makeNow) {
                    isFound = true;
                    break;
                }
            }
            if (isFound == false) {
                selectOptions += `<option>${makeNow}</option>`;
                makes.push(makeNow);
            }
        }
        $("#select-make").html(selectOptions);
    }

    function showInf(makeName, modelName) {
        $.ajax({
            type: "POST",
            url: "/getChoicesInf",
            data: `make=${makeName}&model=${modelName}`,
            success: function (data) {
                engineTable(data);
            },
            error: function (data) {
                if (data.readyState == 0) {
                    alert("Connection failure!");
                    return;
                }
            },
            async: true
        });
    }

    function engineTable(data) {
        optionSets = data.optionSets;
        var html = "";
        html += '<table class="table table-bordered table-hover">';
        for (var i = 0; i < optionSets.length; i++) {
            var opSet = optionSets[i];
            html += "<tr>";
            {
                html += `<td class="text-center"><b>${opSet.name}</b></td>`;
                html += "<td>";
                {
                    html += `<select class="form-control" id="select-optionset-${i}">`;
                    var options = opSet.options;
                    for (var j = 0; j < options.length; j++) {
                        html += `<option>${options[j]}</option>`;
                    }
                    html += `</select>`;
                }
                html += "</td>";
            }
            html += "</tr>";
        }
        html += '</table>';
        $('#choice-tabel').html(html);
    }
});