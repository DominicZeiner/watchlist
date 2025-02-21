document.addEventListener('DOMContentLoaded', function() {
    var table = $('#watchlistTable').DataTable();
    fetchWatchlistData();
    fetchAvailableMedia();

    $('#addToWatchlistForm').on('submit', function(event) {
        event.preventDefault();
        addToWatchlist();
    });
});

function fetchWatchlistData() {
    fetch('/api/watchlist')
        .then(response => response.json())
        .then(data => {
            var table = $('#watchlistTable').DataTable();
            table.clear();
            data.forEach(item => {
                table.row.add([
                    item.imdbId,
                    item.title,
                    item.imdbRating,
                    item.runtime,
                    item.status,
                    item.dateWatched,
                    item.episodesWatched,
                    item.notes,
                    item.isFavorite ? 'Yes' : 'No'
                ]).draw();
            });
        })
        .catch(error => console.error('Error fetching watchlist data:', error));
}

function fetchAvailableMedia() {
    fetch('/api/watchlist/available')
        .then(response => response.json())
        .then(data => {
            var imdbIdSelect = document.getElementById('imdbId');
            imdbIdSelect.innerHTML = '';
            data.forEach(media => {
                var option = document.createElement('option');
                option.value = media.imdbId;
                option.text = `${media.imdbId} - ${media.title}`;
                imdbIdSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching available media:', error));
}

function addToWatchlist() {
    var formData = new FormData(document.getElementById('addToWatchlistForm'));
    var jsonData = {};
    formData.forEach((value, key) => { jsonData[key] = value });

    fetch('/api/watchlist', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)
    })
        .then(response => response.json())
        .then(data => {
            $('#addToWatchlistModal').modal('hide');
            fetchWatchlistData();
            fetchAvailableMedia()
        })
        .catch(error => console.error('Error adding to watchlist:', error));
}