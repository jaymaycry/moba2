import Expo from 'expo';
import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import SearchView from './src/Search/SearchView';

class App extends React.Component {
      constructor(props) {
        super(props);
        this.state = {stations: []};
    }

    updateStations (stations){
      console.log('invoked updateStations');
      console.log(stations);
      this.setState({stations});
    }

  render() {
    return (
      <View style={styles.container}>
        <SearchView onSearchStations={(stations) => this.updateStations(stations)}/>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});

Expo.registerRootComponent(App);
