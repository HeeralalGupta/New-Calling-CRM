// Define the data for Lok Sabha -> Vidhan Sabha -> Sub Division
const lokSabhaData = {
    'Palamu(30-Palamu)': {
        'Panki': ['Panki'],
        'Medininagar': ['Medininagar','Sadar Medininagar', 'Chaipur', 'Patan', 'Vishrampur', 'Panki', 'Manatu', 'Sabarwa', 'Nilambar-Pitambar', 'Tarhasi', 'Pandwa', 'Pandu', 'Utari Road', 'Nawabazar'],
        'Vishrampur': ['Vishrampur'],
        'Chhattarpur': ['Chhattarpur','Hariharganj','Naudihabazar','Pipra'],
        'Hussainabad': ['Hussainabad', 'Mohamadganj']
    }
};

// Function to populate dropdowns
function populateDropdown(dropdown, options) {
    dropdown.innerHTML = '<option value="">Select Option</option>';
    options.forEach(option => {
        let optionElement = document.createElement('option');
        optionElement.value = option;
        optionElement.textContent = option;
        dropdown.appendChild(optionElement);
    });
}

// Function to select an option in a dropdown
function selectOption(dropdown, value) {
    const options = Array.from(dropdown.options);
    const matchingOption = options.find(option => option.value === value);
    if (matchingOption) {
        dropdown.value = value;
    }
}

// Populate the initial Lok Sabha dropdown
populateDropdown(document.getElementById('lokSabhaDropdown'), Object.keys(lokSabhaData));

// Event listener for Sub Division dropdown
document.getElementById('subDivisionDropdown').addEventListener('change', function() {
    const selectedSubDivision = this.value;
    let found = false;

    // Iterate through lokSabhaData to find the corresponding Lok Sabha and Vidhan Sabha
    for (let lokSabha in lokSabhaData) {
        for (let vidhanSabha in lokSabhaData[lokSabha]) {
            if (lokSabhaData[lokSabha][vidhanSabha].includes(selectedSubDivision)) {
                // Automatically populate Lok Sabha and Vidhan Sabha dropdowns
                selectOption(document.getElementById('lokSabhaDropdown'), lokSabha);
                populateDropdown(document.getElementById('vidhanSabhaDropdown'), Object.keys(lokSabhaData[lokSabha]));
                selectOption(document.getElementById('vidhanSabhaDropdown'), vidhanSabha);
                found = true;
                break;
            }
        }
        if (found) break;
    }
});

// Event listener for Lok Sabha dropdown
document.getElementById('lokSabhaDropdown').addEventListener('change', function() {
    const selectedLokSabha = this.value;
    const vidhanSabhaDropdown = document.getElementById('vidhanSabhaDropdown');
    const subDivisionDropdown = document.getElementById('subDivisionDropdown');
    
    // Reset Vidhan Sabha and Sub Division dropdowns
    vidhanSabhaDropdown.innerHTML = '<option value="">Select Option</option>';
    subDivisionDropdown.innerHTML = '<option value="">Select Option</option>';
    
    if (selectedLokSabha && lokSabhaData[selectedLokSabha]) {
        populateDropdown(vidhanSabhaDropdown, Object.keys(lokSabhaData[selectedLokSabha]));
    }
});

// Event listener for Vidhan Sabha dropdown
document.getElementById('vidhanSabhaDropdown').addEventListener('change', function() {
    const selectedLokSabha = document.getElementById('lokSabhaDropdown').value;
    const selectedVidhanSabha = this.value;
    const subDivisionDropdown = document.getElementById('subDivisionDropdown');
    
    if (selectedLokSabha && selectedVidhanSabha && lokSabhaData[selectedLokSabha][selectedVidhanSabha]) {
        populateDropdown(subDivisionDropdown, lokSabhaData[selectedLokSabha][selectedVidhanSabha]);
    }
});
