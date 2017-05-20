function onSuccess(data) {
    var locations = [];
    $('#notActivatedLocationsTable').bootstrapTable({});
    data.forEach(function (item, i, arr) {
        var workingDays = [];
        if (item["workingDays"]) {
            item["workingDays"].forEach(function (item, p2, p3) {
                var workingDay = "";
                var workingTime = "";
                workingDay += item["day"];
                item["time"].forEach(function (item, p, p) {
                    workingTime += item["from"] + "-" + item["to"] + ";";
                });
                workingDay += ": " + workingTime;
                workingDays.push(workingDay)
            })
        }
        var location = {
            id: item["id"],
            name: item["name"],
            description: item["description"],
            coordinates: "<a href = 'http://maps.google.com/maps?q=" + item["coordinates"]["lat"] + "," + item["coordinates"]["lon"] + "' target='_blank'>Google Maps</a>",
            category: item["category"]["name"],
            workingDays: workingDays.join("<br/>")
        };
        locations.push(location);
    });
    $('#notActivatedLocationsTable').bootstrapTable("load", locations);
}
function loadUnactivatedLocations() {
    $.get({
        url: "/locations?longitude=50.0&latitude=50.0&distance=100000km&isActivated=false",
        success: onSuccess
    });
}

function activateLocation(id) {
    $.ajax({
        url: '/locations/activate',
        type: 'PUT',
        contentType: "application/json",
        data: JSON.stringify({id: id}),
        success: function (result) {
            loadUnactivatedLocations();
        }
    });
}

function deleteLocation(id) {
    $.ajax({
        url: '/locations/' + id,
        type: 'DELETE',
        success: function (result) {
            loadUnactivatedLocations();
        }
    });
}

function tableActions(value, row, index) {
    return [
        '<button type="button" class="btn btn-primary" onclick="activateLocation(\'' + row.id + '\')">Activate</button>',
        '<button type="button" class="btn btn-danger col-md-offset-1" onclick="deleteLocation(\'' + row.id + '\')">Delete</button>'
    ].join('');
}

window.onload = loadUnactivatedLocations;