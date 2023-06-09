
<script>
    function showToast(message, isSuccess) {
        var toastClass = isSuccess ? "toast-success" : "toast-error";
        var toastElement = document.getElementById("toast");
        toastElement.innerHTML = message;
        toastElement.classList.add(toastClass);
        toastElement.classList.add("show");
        setTimeout(function() {
            toastElement.classList.remove("show");
            toastElement.classList.remove(toastClass);
        }, 3000);
    }
</script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"
        integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/additional-methods.min.js"></script>
<link rel="stylesheet" href="style.css">
