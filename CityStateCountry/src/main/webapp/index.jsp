<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dependent Select Option</title>
    </head>
    <body class="cyan">
                    <h5>Dependent Select Item</h5>
                <form>
                        <select id="country">
                            <option>Select Country</option>
                        </select>
                     
                        <select id="state">
                            <option>Select State</option>
                        </select>
              
                        <select id="city">
                            <option>Select City</option>
                        </select>
                     
                        <button class="btn">Submit</button>
                    </div>
                </form>
            </div>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
      
        <script type="text/javascript">
            $(document).ready(function () {
                $.ajax({
                    url: "GetCountryStateServlet",
                    method: "GET",
                    data: {operations: 'country'},
                    success: function (data, textStatus, jqXHR) {
                        console.log(data);
                        let obj = $.parseJSON(data);
                        $.each(obj, function (key, value) {
                            $('#country').append('<option value="' + value.id + '">' + value.name + '</option>')
                        });
                        $('select').formSelect();
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        $('#country').append('<option>Country Unavailable</option>');
                    },
                    cache: false
                });
                
                $('#country').change(function () {
                    $('#state').find('option').remove();
                    $('#state').append('<option>Select State</option>'); 
                    $('#city').find('option').remove();
                    $('#city').append('<option>Select City</option>');

                    let cid = $('#country').val();
                    let data = {
                        operations: "state",
                        id: cid
                    };

                    $.ajax({
                        url: "GetCountryStateServlet",
                        method: "GET",
                        data: data,
                        success: function (data, textStatus, jqXHR) {
                            console.log(data);
                            let obj = $.parseJSON(data);
                            $.each(obj, function (key, value) {
                                $('#state').append('<option value="' + value.id + '">' + value.name + '</option>')
                            });
                            $('select').formSelect();
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            $('#state').append('<option>State Unavailable</option>');
                        },
                        cache: false
                    });
                });
                
                $('#state').change(function () {
                    $('#city').find('option').remove();
                    $('#city').append('<option>Select City</option>');

                    let sid = $('#state').val();
                    let data = {
                        operations: "city",
                        id: sid
                    };

                    $.ajax({
                        url: "GetCountryStateServlet",
                        method: "GET",
                        data: data,
                        success: function (data, textStatus, jqXHR) {
                            console.log(data);
                            let obj = $.parseJSON(data);
                            $.each(obj, function (key, value) {
                                $('#city').append('<option value="' + value.id + '">' + value.name + '</option>')
                            });
                            $('select').formSelect();
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            $('#city').append('<option>City Unavailable</option>');
                        },
                        cache: false
                    });
                });

            });
        </script>
    </body>
</html>
