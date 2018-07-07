import React, {Component} from 'react';
import Head from './part/Head';
import './css/home.css'

class Home extends Component{
    render(){
        return(
            <div className="home-page">
                <Head/>
                <div className="home-content">
                    <legend align="center"><h1 className="home-title">DOTA 2</h1></legend>
                    <iframe src="http://www.youtube.com/embed/-cSFPIwMEq4?rel=0&hd=1" className="home-iframe"/>
                </div>
            </div>
        )
    }
}

export default Home;
