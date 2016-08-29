package io.github.spharris.datastructures;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class HashMapTest {
  
  private Map<String, Integer> testMap;
  
  @Rule public ExpectedException thrown = ExpectedException.none();
  
  @Before
  public void initializeMap() {
    testMap = new HashMap<>();
  }
  
  @Test
  public void sizeOfEmptyIsZero() {
    assertThat(testMap.size()).isEqualTo(0);
  }
  
  @Test
  public void isEmptyReturnsTrueWhenEmpty() {
    assertThat(testMap.isEmpty()).isTrue();
  }
  
  @Test
  public void isEmptyReturnsFalseWhenNotEmpty() {
    testMap.put("One", 1);
    assertThat(testMap.isEmpty()).isFalse();
  }
  
  @Test
  public void sizeEqualsActualSize() {
    testMap.put("One", 1);
    assertThat(testMap.size()).isEqualTo(1);
  }
  
  @Test
  public void insertManyElements() {
    int numElements = 100;
    for (int i = 0; i < numElements; i++) {
      testMap.put(String.valueOf(i), i);
    }
    
    assertThat(testMap.size()).isEqualTo(numElements);
  }
  
  @Test
  public void retrieveAnElement() {
    String key = "One";
    int value = 1;
    
    testMap.put(key, value);
    
    assertThat(testMap.get(key)).isEqualTo(value);
  }
  
  @Test
  public void retrieveNonExistentElementIsNull() {
    String key = "One";
    int value = 1;
    
    testMap.put(key, value);
    
    assertThat(testMap.get("Two")).isNull();
  }
  
  @Test
  public void retrieveElementFromEmpty() {
    assertThat(testMap.get("Two")).isNull();
  }
  
  @Test
  public void containsKeyOnEmptyIsFalse() {
    assertThat(testMap.containsKey("Key")).isFalse();
  }
  
  @Test
  public void containsRealElementIsTrue() {
    String key = "One";
    int value = 1;
    
    testMap.put(key, value);
    
    assertThat(testMap.containsKey(key)).isTrue();
  }
}
