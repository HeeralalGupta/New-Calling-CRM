
  let userId;
  let userEmail;

  function openOtpDialog(id, email) {
    userId = id;
    userEmail = email;
    // Send OTP to user's email
    fetch(`/sendOtp?userId=${id}&email=${email}`)
      .then(response => response.json())
      .then(data => {
        if (data.success) {
          document.getElementById('otpDialog').style.display = 'block';
        } else {
          alert('Error sending OTP. Please try again.');
        }
      })
      .catch(error => console.log('Error:', error));
  }

  function closeOtpDialog() {
    document.getElementById('otpDialog').style.display = 'none';
  }

  function verifyOtp() {
    const otp = document.getElementById('otpInput').value;
    fetch(`/verifyOtp?userId=${userId}&otp=${otp}`)
      .then(response => response.json())
      .then(data => {
        if (data.success) {
          alert('User deleted successfully.');
          closeOtpDialog();
        } else {
          alert('Invalid OTP. Please try again.');
        }
      })
      .catch(error => console.log('Error:', error));
  }
