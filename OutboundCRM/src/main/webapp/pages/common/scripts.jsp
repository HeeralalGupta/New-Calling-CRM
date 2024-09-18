<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<body>
 <!-- plugins:js -->
    <script src="assets/vendors/js/vendor.bundle.base.js"></script>
    <!-- endinject -->
    <!-- Plugin js for this page -->
    <script src="assets/vendors/chart.js/Chart.min.js"></script>
    <script src="assets/js/jquery.cookie.js" type="text/javascript"></script>
    <!-- End plugin js for this page -->
    <!-- inject:js -->
    <script src="assets/js/off-canvas.js"></script>
    <script src="assets/js/hoverable-collapse.js"></script>
    <script src="assets/js/misc.js"></script>
    <!-- endinject -->
    <!-- Custom js for this page -->
    <script src="assets/js/dashboard.js"></script>
    <script src="assets/js/todolist.js"></script>
    <script src="assets/js/rural-search.js"></script>
    <script src="assets/js/urban-search.js"></script>
    <script src="assets/js/mail-otp.js"></script>
    <!-- End custom js for this page -->
    <script src="assets/js/ruralJharkhandData.js"></script>
    <script src="assets/js/urbanJharkhandData.js"></script>
    <script src="assets/js/assembly-constituent.js"></script>
    <script src="assets/js/pagination.js"></script>
    
    <!-- For Calling Type -->
    <script>
		document.getElementById('connectionType').addEventListener('change', function() {
		    var connectedDropdown = document.getElementById('connectedDropdown');
		    var notConnectedDropdown = document.getElementById('notConnectedDropdown');
		    
		    if (this.value === 'connected') {
		        connectedDropdown.style.display = 'block';
		        notConnectedDropdown.style.display = 'none';
		    } else if (this.value === 'notConnected') {
		        notConnectedDropdown.style.display = 'block';
		        connectedDropdown.style.display = 'none';
		    } else {
		        connectedDropdown.style.display = 'none';
		        notConnectedDropdown.style.display = 'none';
		    }
		});
	</script>
	<!-- For Rural Urban -->
	<script>
		document.getElementById('ruralRadio').addEventListener('change', function() {
		    document.getElementById('ruralInputs').style.display = 'block';
		    document.getElementById('urbanInputs').style.display = 'none';
		    
		 // Clear the urban inputs
	        clearInputs(document.getElementById('urbanInputs'));
		});
		
		document.getElementById('urbanRadio').addEventListener('change', function() {
		    document.getElementById('urbanInputs').style.display = 'block';
		    document.getElementById('ruralInputs').style.display = 'none';
		    
		 // Clear the rural inputs
	        clearInputs(document.getElementById('ruralInputs'));
		});
		
		function clearInputs(section) {
	        section.querySelectorAll('select, input').forEach(input => {
	            if (input.tagName.toLowerCase() === 'select') {
	                input.selectedIndex = 0; // Reset select to default option
	            } else {
	                input.value = ''; // Clear input value
	            }
	        });
	    }
		
	</script>
	
	<script>
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
	</script>
	
</body>
</html>