package com.yw.observer.proxy.proxy2;

public class OneKeyCareManager extends BaseManager<IOneKeyCareListener> {

    private OneKeyCareManager() {
    }

    private static OneKeyCareManager m_Instance;

    public static OneKeyCareManager getInstance() {
        if (m_Instance == null) {
            m_Instance = new OneKeyCareManager();
        }
        return m_Instance;
    }

    private final ListenerProxy<IOneKeyCareListener> mListenerProxy = new ListenerProxy<>(IOneKeyCareListener.class);

    public IOneKeyCareListener listener = mListenerProxy.createProxyListener();

    @Override
    public void addListener(IOneKeyCareListener m_listener) {
        if (m_listener == null) {
            return;
        }
        mListenerProxy.add(m_listener);
    }

    @Override
    public void removeListener(IOneKeyCareListener m_listener) {
        if (m_listener == null) {
            return;
        }
        mListenerProxy.remove(m_listener);
    }

}
