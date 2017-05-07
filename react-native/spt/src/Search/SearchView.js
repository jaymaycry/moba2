import React, { Component } from 'react';
import { StyleSheet, AppRegistry, TextInput, Button, View } from 'react-native';
import Search from './Search';

export default class SearchView extends Component {
    constructor(props) {
        super(props);
    }

fetchLocation (location) {
        return fetch(`http://transport.opendata.ch/v1/locations?query=${location}`)
      .then((response) => response.json())
      .then((responseJson) => {
        this.props.onSearchStations(responseJson.stations);
      })
      .catch((error) => {
        console.error(error);
      });

      
}

    render() {
    return (
        <View>
            <Search onSearchStations={(location) => this.fetchLocation (location)} />
        </View>
    );
    }
}

const styles = StyleSheet.create({
  input: {
    height: 40
  },
});