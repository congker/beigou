
public class CacheTests {

    @Test
    @ApiOperation(value = "guava缓存", notes = "guava")
    public void TestCache() throws InterruptedException {
        CacheLoader<String, ExpMeta> cacheLoader = new CacheLoader<String, ExpMeta>() {
            // 如果找不到元素，会调用这里
            @Override
            public ExpMeta load(String s) {
                return null;
            }
        };
        LoadingCache<String, ExpMeta> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000) // 容量
                .expireAfterWrite(3, TimeUnit.SECONDS) // 过期时间
//                .removalListener(new MyRemovalListener()) // 失效监听器
                .build(cacheLoader);
        loadingCache.put("key1", new ExpMeta("key1", "v1"));
        loadingCache.put("key2", new ExpMeta("key2", "v2"));
        loadingCache.put("key3", new ExpMeta("key3", "v3"));

        loadingCache.invalidate("猫"); // 手动失效
        Object animal = loadingCache.getIfPresent("key1");
        System.out.println(animal);
        Thread.sleep(4 * 1000);
        System.out.println(loadingCache.getIfPresent("狼"));
    }

    @Test
    @ApiOperation(value = "guava缓存", notes = "guava")
    public void TestCache2() throws InterruptedException, ExecutionException {
        CacheLoader<String, ExpMeta> cacheLoader = new CacheLoader<String, ExpMeta>() {
            // 如果找不到元素，会调用这里
            @Override
            public ExpMeta load(String s) {
                return null;
            }
        };
        LoadingCache<String, ExpMeta> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000) // 容量
                .expireAfterWrite(3, TimeUnit.SECONDS) // 过期时间
//                .removalListener(new MyRemovalListener()) // 失效监听器
                .build(cacheLoader);


        loadingCache.put("key1", new ExpMeta("key1", "v1"));
        loadingCache.put("key2", new ExpMeta("key2", "v2"));
        loadingCache.put("key3", new ExpMeta("key3", "v3"));

        loadingCache.invalidate("custom-key"); // 手动失效
        ExpMeta uni = loadingCache.getIfPresent("key1");
        System.out.println(uni);
        Thread.sleep(4 * 1000);
        // 狼已经自动过去，获取为 null 值报错
        System.out.println(loadingCache.getIfPresent("custom-key"));
    }

}
