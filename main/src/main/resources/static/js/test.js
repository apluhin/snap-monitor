function reviewData(data1) {
    var arr1 = [['Router', 'Load']];
    for (var i = 0; i < data1.length; i++) {
        var date = new Date(data1[i].timestamp);
        var str = date.getMinutes();
        if (date.getMinutes() < 10) {
            str = "0" + str;
        }
        data1[i].timestamp = date.getHours() + ":" + str;
        arr1[i + 1] = [data1[i].timestamp, data1[i].load]
    }
    if (data1.length == 0) {
        arr1[1] = ["00:00", 0];
    }
    return arr1;
}
function convert(str) {
    str = str + ""
    if (str.length == 1) {
        return "0" + str;
    } else {
        return str;
    }
}
var Main = React.createClass({


    getInitialState: function () {

        var to = new Date();
        to.setTime(to.getTime() + 360000)
        var to1 = to.getUTCFullYear() + "-" + convert(to.getMonth() + 1) + "-" + convert(to.getDate()) + " " + convert(to.getHours()) + ":" + convert(to.getMinutes()) + ":00";
        var a = to.getTime();
        a = a - 3600000;
        to.setTime(a)


        var from1 = to.getUTCFullYear() + "-" + convert(to.getMonth() + 1) + "-" + convert(to.getDate()) + " " + convert(to.getHours()) + ":" + convert(to.getMinutes()) + ":00";
        var Data = {
            array: [["Router", "Load"], ["00:00", 0]],
            timeTo: to1,
            timeFrom: from1
        }
        return {data: Data}
    },

    componentWillUpdate: function (nextProps, nextState) {
        console.log(nextProps)
        console.log(nextState)

    },

    componentDidMount: function () {
        this.send();
    },

    componentDidUpdate: function (prevProps, prevState) {
        if (this.state.data.timeFrom.length == 19 && this.state.data.timeTo.length == 19) {
            this.send();
        }
    },

    generateView: function () {
        var a = this.props.device.ipAddress;
        $.get("/device?action=cpu&address=" + a.replace("/", "")).done(function (data1) {
            var arr1 = reviewData(data1);
            generateTable(arr1)
        });

    },

    setFrom: function () {
        this.state.data.timeFrom = event.target.value;
        console.log(this.state.data.timeFrom)
        var Data = {
            array: this.state.data.array,
            timeTo: this.state.data.timeTo,
            timeFrom: event.target.value
        }
        this.setState({data: Data});
    },

    setTo: function () {
        var Data = {
            array: this.state.data.array,
            timeTo: event.target.value,
            timeFrom: this.state.data.timeFrom
        }
        this.setState({data: Data});
    },

    send: function () {
        $.get('/device?action=cpu_interval&address=' + this.props.device.ipAddress.replace("/", "") + '&from=' + this.state.data.timeFrom + "&to=" + this.state.data.timeTo).done(function (data) {
            generateTable(reviewData(data))
        }.bind(this));
    },

    render: function () {

        var str = this.props.device.vendor.toLowerCase();
        if (this.state.typeRender == 'nothing') {
            return <div></div>
        }
        return (<div id="device-panel">
            <div id="main-panel">

                Имя устройства: <b>{this.props.device.name}</b><br></br>
                IP адрес устройства: <b>{this.props.device.ipAddress}</b><br></br>
                Вендор <b>{str}</b><br></br>
                <b>Выбор времени</b><br></br>
                Загрузка с <input type="text" onChange={this.setFrom} value={this.state.data.timeFrom}/> до <input
                onChange={this.setTo} value={this.state.data.timeTo} type="text"/><br></br>
                <b onClick={this.send}>Отправить</b><br></br>
            </div>
        </div>);
    }

});


function generateTable(data) {
    google.charts.load('current', {'packages': ['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {

        var d = google.visualization.arrayToDataTable(data);

        var options = {
            title: 'Load',
            hAxis: {title: 'Time', titleTextStyle: {color: '#333'}},
            vAxis: {minValue: 0}
        };

        var chart = new google.visualization.AreaChart(document.getElementById('placeholder'));
        chart.draw(d, options);
    }
}

var Header = React.createClass({


    render: function () {
        return (
            <div className="navbar navbar-default" id="header">
                <a className="navbar-brand">Monitoring</a>
                <ul style={{cursor: "pointer"}} className="nav navbar-nav" id="menu">
                    <MainLink/>
                    <SomeLink/>
                </ul>
            </div>
        );
    }
});

var MainListOfDevice = React.createClass({

    getInitialState: function () {
        $("#container").html("");
        $("#container1").html("");
        return null;
    },

    handlerMain: function (el) {
        $("#container").removeClass("non-visible");
        $("#container1").removeClass("non-visible");
        $("#placeholder").removeClass("non-visible");
        ReactDOM.render(
            <Main device={el}/>,
            document.getElementById('container1')
        )
        ;

    },

    getInitialState: function () {
        return {listDevices: []}
    },

    componentDidMount: function () {
        $.get('/device?action=all').done(function (data) {
            this.setState({listDevices: data});
        }.bind(this));
    },

    render: function () {
        var console = this;
        return <ul id="list-texts" className="list-group">
            {

                this.state.listDevices.map(function (el, index) {

                    if (el.online == false) {
                        return <li className="list-group-item" style={{'background': 'red'}} key={index}
                                   onClick={() => console.handlerMain(el)}>{el.ipAddress}
                        </li>
                    } else {
                        return <li className="list-group-item" key={index}
                                   onClick={() => console.handlerMain(el)}>{el.ipAddress}
                        </li>
                    }


                })
            }
        </ul>
    }


});


var MainLink = React.createClass({

    getInitialState: function () {
        $("#container").html("");
        return null;
    },

    handlerMain: function () {
        $("#container").removeClass("non-visible");
        $("#container1").addClass("non-visible")
        ReactDOM.render(
            <MainListOfDevice/>,
            document.getElementById('container')
        );

    },

    render: function () {
        return ( <li onClick={this.handlerMain}><a>Monitoring</a></li>)
    }
});

var SomeLink = React.createClass({

    getInitialState: function () {
        $("#container").html("");
        $("#container1").html("");
        $("#placeholder").html("");
        return null;
    },


    handlerMain: function () {
        $("#placeholder").html("");
        $("#container1").removeClass("non-visible");
        $("#container").addClass("non-visible");
        ReactDOM.render(
            <Device/>,
            document.getElementById('container1')
        );

    },

    render: function () {
        return ( <li onClick={this.handlerMain}><a>Devices</a></li>)
    }
});

var Device = React.createClass({

    getInitialState: function () {
        $("#container").addClass("non-visible");

        var hash = {
            typeHash: null,
            hashPassword: null
        }
        var encryption = {
            typeEncrypt: null,
            encryptionPassword: null,
        }
        var snmp = {
            hash: hash,
            encryption: encryption,
            version: "none",
            username: null
        }
        var Device = {
            vendor: null,
            address: null,
            snmp: snmp
        }
        return {Device: Device}
    },

    render: function () {
        console.log(this.state.Device.snmp.version)
        if (this.state.Device.snmp.version == "v3") {
            return <div>
                <p>
                    value="version" v3<select onChange={this.addVersion} type="text">
                    <option>v3</option>
                    <option>v1</option>
                    <option>v2</option>
                </select>
                </p>
                <p>
                    value="Vendor"Cisco<input onChange={this.addVendor} type="text"/>
                </p>
                <p>
                    value="Address"192.168.0.1<input onChange={this.addAddress} type="text"/>
                </p>


                <p>
                    value="username"operator<input onChange={this.addUsername} type="text"/>
                </p>
                <p>
                    value="typeHash" MD5<input onChange={this.addTypeHash} type="text"/>
                </p>
                <p>
                    value="HashPassword" AuthPassw0rd<input onChange={this.addHashPassword} type="text"/>
                </p>
                <p>
                    value="typeEncrypt" DES<input onChange={this.addTypeEncrypt} type="text"/>
                </p>
                <p>
                    value="EncryptPassword" EncryptionPassw0rd<input onChange={this.addEncryptPassword} type="text"/>
                </p>
                <p>
                    <input type="button" onClick={this.sendVal}/>
                </p>
            </div>
        } else {
            return <p>
                value="version" v3<select onChange={this.addVersion} type="text">
                <option>v1</option>
                <option>v2</option>
                <option>v3</option>
            </select>
            </p>;
        }

    },

    sendVal: function () {
        var stringify = JSON.stringify(this.state.Device);
        stringify = '{ "device" : ' + stringify + '}}';
        $.post("/device?action=add", {device: stringify}, function () {

        }, "json")
    },


    addVendor: function (event) {
        this.state.Device.vendor = event.target.value
    },

    addAddress: function (event) {
        this.state.Device.address = event.target.value
    },

    addVersion: function (event) {
        var Dev = this.state.Device;
        Dev.snmp.version = event.target.value;
        this.setState({
            Device: Dev
        });
    },

    addUsername: function (event) {
        this.state.Device.snmp.username = event.target.value
    },

    addTypeHash: function (event) {
        this.state.Device.snmp.hash.typeHash = event.target.value
    },

    addHashPassword: function (event) {
        this.state.Device.snmp.hash.hashPassword = event.target.value

    },

    addTypeEncrypt: function (event) {
        this.state.Device.snmp.encryption.typeEncrypt = event.target.value
    },
    addEncryptPassword: function (event) {
        this.state.Device.snmp.encryption.encryptionPassword = event.target.value
    }


});

ReactDOM.render(
    <Header/>,
    document.getElementById('header')
);
