document.addEventListener('DOMContentLoaded', function() {
    var table = $('#mediaTable').DataTable();
    fetchMediaData();

    $('#addMediaForm').on('submit', function(event) {
        event.preventDefault();
        addMedia();
    });
});

function fetchMediaData() {
    fetch('/api/media')
        .then(response => response.json())
        .then(data => {
            var table = $('#mediaTable').DataTable();
            table.clear();
            data.forEach(item => {
                table.row.add([
                    item.imdbId,
                    item.title,
                    item.genre,
                    item.imdbRating,
                    item.runtime,
                    `<button class="btn btn-danger" onclick="deleteMedia('${item.imdbId}')">Delete</button>`
                ]).draw();
            });
        })
        .catch(error => console.error('Error fetching media data:', error));
}

function addMedia() {
    var formData = new FormData(document.getElementById('addMediaForm'));
    var jsonData = {};
    formData.forEach((value, key) => { jsonData[key] = value });

    fetch('/api/media', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)
    })
        .then(response => response.json())
        .then(data => {
            $('#addMediaModal').modal('hide');
            fetchMediaData();
        })
        .catch(error => console.error('Error adding media:', error));
}

function deleteMedia(imdbId) {
    fetch(`/api/media/${imdbId}`, {
        method: 'DELETE'
    })
        .then(response => {
            fetchMediaData();
        })
        .catch(error => console.error('Error deleting media:', error));
}