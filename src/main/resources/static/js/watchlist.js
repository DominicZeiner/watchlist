document.addEventListener('DOMContentLoaded', function() {
    var table = $('#watchlistTable').DataTable();
    fetchWatchlistData();
    fetchAvailableMedia();

    $('#addToWatchlistForm').on('submit', function(event) {
        event.preventDefault();
        addToWatchlist();
    });

    $('#updateWatchlistOwnRatingForm').on('submit', function(event) { // Fix here
        event.preventDefault();
        updateWatchlistOwnRating();
    });

    $('#watchlistTable tbody').on('click', 'td:nth-child(4)', function() {
        var rowData = table.row(this).data();
        document.getElementById('imdbIdUpdateModal').value = rowData[0]; // Set the imdbId
        $('#updateWatchlistOwnRatingModal').modal('show'); // Show the modal
    });
});

function updateWatchlistOwnRating() {
    console.log('Updating watchlist own rating');
    const imdbId = document.getElementById('imdbIdUpdateModal').value;
    const ownRating = document.getElementById('ownRatingUpdateModal').value;

    fetch(`/api/watchlist/${imdbId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ rating: parseFloat(ownRating) }) // Fix here
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to update rating');
            }
            return response.json();
        })
        .then(data => {
            console.log('Success:', data);
            $('#updateWatchlistOwnRatingModal').modal('hide'); // Fix here
            fetchWatchlistData();
        })
        .catch(error => console.error('Error updating watchlist:', error));
}


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
                    item.ownRating,
                    item.runtime,
                    item.status,
                    item.dateWatched,
                    item.episodesWatched,
                    item.notes,
                    item.isFavorite ? 'Yes' : 'No',
                    item.type
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