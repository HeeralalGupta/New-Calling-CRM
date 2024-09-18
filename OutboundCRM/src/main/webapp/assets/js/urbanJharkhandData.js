const jharkhandData = {
    "District1": {
        "PoliceStation1": ["Municipality1", "Municipality2"],
        "PoliceStation2": ["Municipality3", "Municipality4"]
    },
    "District2": {
        "PoliceStation3": ["Municipality5", "Municipality6"],
        "PoliceStation4": ["Municipality7", "Municipality8"]
    }
};

// Populate districts in the district dropdown
const districtDropdown = document.getElementById('urbanDistrictDropdown');
Object.keys(jharkhandData).forEach(district => {
    const option = document.createElement('option');
    option.value = district;
    option.textContent = district;
    districtDropdown.appendChild(option);
});

// Event listener to update Police station dropdown based on district selection
districtDropdown.addEventListener('change', function () {
    const policeStationDropdown = document.getElementById('policeStationDropdown');
    const municipalityDropdown = document.getElementById('municipalityDropdown');
    const selectedDistrict = this.value;

    // Clear existing options
    policeStationDropdown.innerHTML = '<option value="">Select Police Station</option>';
    municipalityDropdown.innerHTML = '<option value="">Select Municipality</option>';

    if (selectedDistrict && jharkhandData[selectedDistrict]) {
        const policeStations = Object.keys(jharkhandData[selectedDistrict]);
        policeStations.forEach(police => {
            const option = document.createElement('option');
            option.value = police;
            option.textContent = police;
            policeStationDropdown.appendChild(option);
        });
    }
});

// Event listener to update municipality dropdown based on police station selection
document.getElementById('policeStationDropdown').addEventListener('change', function () {
    const municipalityDropdown = document.getElementById('municipalityDropdown');
    const selectedDistrict = document.getElementById('districtDropdown').value;
    const selectedPoliceStation = this.value;

    // Clear existing options
    municipalityDropdown.innerHTML = '<option value="">Select Municipality</option>';

    if (selectedDistrict && selectedPoliceStation && jharkhandData[selectedDistrict][selectedPoliceStation]) {
        const municipalities = jharkhandData[selectedDistrict][selectedPoliceStation];
        municipalities.forEach(municipality => {
            const option = document.createElement('option');
            option.value = municipality;
            option.textContent = municipality;
            municipalityDropdown.appendChild(option);
        });
    }
});