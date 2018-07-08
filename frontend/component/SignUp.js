import React, {Component} from 'react';
import Head from './part/Head'
import $ from "jquery";

class SignUp extends Component {
    constructor(props){
        super(props);
        this.state = {
            errorIsHidden: true,
            successIsHidden: true,
            successMessage: "",
            errorMessage: "",
            url: "/create/user?login=",
        };
    }

    buttonHandle(){
        let doRequest = true;
        if(this.login.value.length < 3){
            doRequest = false;
            this.setState({
                errorIsHidden: false,
                successIsHidden: true,
                errorMessage: "Логин должен содерржать не менее 3 символов!",
            })
        }
        if (this.password.value.length < 4){
            doRequest = false;
            this.setState({
                errorIsHidden: false,
                successIsHidden: true,
                errorMessage: "Пароль должен содерржать не менее 4 символов!",
            })
        }
        if (this.password.value.localeCompare(this.repeatPassword.value) !== 0){
            doRequest = false;
            this.setState({
                errorIsHidden: false,
                successIsHidden: true,
                errorMessage: "Введенные пароли должны совпадать!",
            })
        }
        if(doRequest){
            $.ajax({
                url: (this.state.url + this.login.value + "&password=" + this.password.value),
                dataType: 'json',
                cache: false,
                success: function (data) {
                    if (typeof data.error === 'undefined'){
                        this.setState({
                            errorIsHidden: true,
                            successIsHidden: false,
                            successMessage: data.value,
                        });
                        this.login.value = "";
                    } else {
                        this.setState({
                            errorIsHidden: false,
                            successIsHidden: true,
                            errorMessage: data.error,
                        });
                    }
                }.bind(this),
                error: function (xhr, status, err) {
                    console.error(this.state.url + this.login.value + "&password=" + this.password.value, status, err.toString());
                }.bind(this)
            });
        }
        this.password.value = "";
        this.repeatPassword.value = "";
    }

    render() {
        return (
            <div>
                <Head/>
                <div hidden={this.state.errorIsHidden} className="alert alert-dismissible alert-danger alert-message">
                    <button type="button" className="close" data-dismiss="alert"
                            onClick={() => {this.setState({errorIsHidden: true})}}>&times;</button>
                    <strong>{this.state.errorMessage}</strong> Пожалуйста, повторите.
                </div>
                <div hidden={this.state.successIsHidden} className="alert alert-dismissible alert-success alert-message">
                    <button type="button" className="close" data-dismiss="alert"
                            onClick={() => {this.setState({successIsHidden: true})}}>&times;</button>
                    <strong>{this.state.successMessage}</strong> Авторизируйтесь с помощью нового логина.
                </div>
                <div className="jumbotron login-content padding-top">
                    <legend align="center">Регистрация</legend>
                    <div className="form-group">
                        <label>Логин</label>
                        <input type="text" name="username" className="form-control" placeholder="Введите ваш логин"
                               ref={(input) => {this.login = input}}/>
                        <small className="form-text text-muted">Логин должен содерржать не менее 3 символов</small>
                    </div>
                    <div className="form-group">
                        <label>Пароль</label>
                        <input type="password" name="password" className="form-control" placeholder="Введите ваш пароль"
                               ref={(input) => {this.password = input}}/>
                        <small className="form-text text-muted">Пароль должен содерржать не менее 4 символов</small>
                    </div>
                    <div className="form-group">
                        <label>Повторите пароль</label>
                        <input type="password" name="password" className="form-control" placeholder="Введите ваш пароль"
                               ref={(input) => {this.repeatPassword = input}}/>
                        <small className="form-text text-muted">Введенные пароли должны совпадать</small>
                    </div>
                    <button onClick={this.buttonHandle.bind(this)} className="btn btn-primary">Оправить</button>
                </div>
            </div>
        )
    }
}

export default SignUp;
