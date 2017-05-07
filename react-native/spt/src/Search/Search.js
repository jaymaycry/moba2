import React, { Component } from 'react';
import { StyleSheet, AppRegistry, TextInput, Button, View } from 'react-native';

export default class Search extends Component {
    constructor(props) {
        super(props);
        this.state = { text: '' };
    }

    searchStations() {
        
    }

    render() {
    return (
        <View>
            <TextInput
            style={styles.input}
            placeholder="e.g. Zürich HB"
            onChangeText={(text) => this.setState({text})}
            value={this.state.text}
            />
            <Button
            onPress={() => this.props.onSearchStations(this.state.text)}
            title="Search"
            accessibilityLabel="Learn more about this purple button"
            />
        </View>
    );
    }
}

const styles = StyleSheet.create({
  input: {
    height: 40
  },
});