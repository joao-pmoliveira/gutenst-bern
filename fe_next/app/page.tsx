import '@/app/globals.css';
import MyLists from '@/components/my-lists-section';
import RecentHistory from '@/components/recent-history-section';
import Recommendations from '@/components/recommendations-section';
import CurrentlyReadingSection from '@/components/sections/CurrentlyReadingSection';
import ToBeRead from '@/components/to-be-read-section';

export default async function Home() {

    return (
        <main className="max-w-7xl mx-auto mb-10 mt-8
            grid grid-cols-[1fr_2fr_1fr] 
            gap-12
        ">
            <CurrentlyReadingSection className="row-span-2" />
            <ToBeRead className="col-span-2 row-span-2" />
            <RecentHistory className="col-span-2" />
            <MyLists className="" />
            <Recommendations className="col-span-full" />
        </main>
    );
}
