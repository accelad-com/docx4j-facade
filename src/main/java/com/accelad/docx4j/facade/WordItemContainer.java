package com.accelad.docx4j.facade;

public interface WordItemContainer extends WordItem {
    public void addItem(WordItem item);

    public void addItem(WordItem item, int position);

    public int getPosition(WordItem item);

    public int getFirstPosition(WordItems items);

    public WordItems getContent();

    public void removeAll(WordItems items);

    IndexedWordItems getIndexedContent();
}
