import React, {Component} from 'react';
import Head from './part/Head'
import {connect} from 'react-redux';
import './css/hero.css';
import {Link} from 'react-router';
import $ from 'jquery';
import ScrollEvent from 'react-onscroll';

const urlData = "https://dota2.ru/";

class Hero extends Component {
    constructor(){
        super();
        this.state = {
            limit: 10,
            offset: 0,
            url: "http://localhost:8000/get/heroes?limit=",
        };
        this.addHeroes = this.addHeroes.bind(this);
        this.handleScrollCallback = this.handleScrollCallback.bind(this);
        this.changeURL = this.changeURL.bind(this);
    }

    componentDidMount(){
        if (this.props.heroes.length < 10){
            this.props.onDeleteHero();
            this.addHeroes();
        }
    }

    addHeroes(){
        $.ajax({
            url: (this.state.url + this.state.limit + "&offset=" + this.state.offset),
            dataType: 'json',
            cache: false,
            success: function (data) {
                data.forEach((hero) => {
                    this.props.onAddHeroes(hero);
                });
                this.state.offset+=10;
            }.bind(this),
            error: function (xhr, status, err) {
                console.error(this.state.url + this.state.limit + "&offset=" + this.state.offset, status, err.toString());
            }.bind(this)
        });
    }

    handleScrollCallback(){
        const scroll_height = $(document).height();
        const scroll_position = $(window).height() + $(window).scrollTop();
        if (scroll_position === scroll_height) {
            if (this.findInput.value === "" || this.findInput.value === null) {
                this.addHeroes();
            }
        }
    }

    changeURL(){
        if (this.typeSelect.value === "Power"){
            this.state.url = "http://localhost:8000/get/heroes/type?type_id=1&limit=";
        } else if (this.typeSelect.value === "Agility") {
            this.state.url = "http://localhost:8000/get/heroes/type?type_id=2&limit=";
        } else if (this.typeSelect.value === "Intelligence"){
            this.state.url = "http://localhost:8000/get/heroes/type?type_id=3&limit=";
        } else {
            this.state.url = "http://localhost:8000/get/heroes?limit=";
        }
    }

    inputChange() {
        if (this.findInput.value !== ""){
            this.state.url = "http://localhost:8000/get/heroes/like?name=" + this.findInput.value + "&limit=";
        } else{
            this.changeURL();
        }
        this.state.offset = 0;
        this.props.onDeleteHero();
        this.addHeroes();
    }

    selectChange(){
        this.changeURL();
        this.props.onDeleteHero();
        this.state.offset = 0;
        this.addHeroes();
    }

    render() {
        return (
            <div>
                <Head/>
                <ScrollEvent handleScrollCallback={this.handleScrollCallback}/>
                <div className="second-header-hero">
                    <h3 className="hero-list-header">
                        <small>Dota 2 Database</small>
                        <br/>
                        All Heroes<br/>
                        <small>Power Agility Intelligence</small>
                    </h3>
                </div>
                <nav className="navbar filter">
                    <input className="btn btn-primary search-by-name" type="text" placeholder="Filter By Name"
                           ref={(input) => {
                               this.findInput = input;
                           }}
                           onChange={this.inputChange.bind(this)}/>
                    <select onChange={this.selectChange.bind(this)} ref={(select) => {
                        this.typeSelect = select
                    }} className="dropdown-header list-group-item-dark select-type">
                        <option>All</option>
                        <option>Power</option>
                        <option>Agility</option>
                        <option>Intelligence</option>
                    </select>
                </nav>
                <div className="content-table jumbotron">
                    <table className="table table-hover">
                        <thead>
                        <tr>
                            <th scope="col"/>
                            <th scope="col">Name</th>
                            <th className="hidden-1217" scope="col">Role</th>
                            <th className="hidden-1217" scope="col">Attack</th>
                            <th className="hidden-1217" scope="col"/>
                        </tr>
                        </thead>
                        <tbody className="table-active">
                        {this.props.heroes.map((hero) =>
                            <tr key={hero.id}>
                                <th><Link to={"/hero/" + hero.id}>
                                    <img className="hero-image" src={urlData + hero.img}/>
                                </Link></th>
                                <th><Link to={"/hero/" + hero.id}
                                          class="hero-name">{hero.name}</Link></th>
                                <th className="hidden-1217"><span className="span-right">{hero.role}</span></th>
                                <th className="hidden-1217"><span className="span-right">{hero.attack.name}</span></th>
                                <th className="hidden-1217"><img className="hero-type-img" src={urlData + hero.type.img}/></th>
                            </tr>
                        )}
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default connect(
    state => ({
        heroes: state.heroes,
    }),
    dispatch => ({
        onAddHeroes: (hero) => {
            dispatch({type: 'ADD_HERO', payload: hero})
        },
        onDeleteHero: () => {
            dispatch({type: 'DELETE_HERO'});
        }
    })
)(Hero);
