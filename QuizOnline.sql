USE [master]
GO
/****** Object:  Database [Quiz Online]    Script Date: 3/19/2021 7:13:12 PM ******/
CREATE DATABASE [Quiz Online]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Quiz Online', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\Quiz Online.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Quiz Online_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\Quiz Online_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [Quiz Online] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Quiz Online].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Quiz Online] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Quiz Online] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Quiz Online] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Quiz Online] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Quiz Online] SET ARITHABORT OFF 
GO
ALTER DATABASE [Quiz Online] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Quiz Online] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Quiz Online] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Quiz Online] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Quiz Online] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Quiz Online] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Quiz Online] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Quiz Online] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Quiz Online] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Quiz Online] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Quiz Online] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Quiz Online] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Quiz Online] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Quiz Online] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Quiz Online] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Quiz Online] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Quiz Online] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Quiz Online] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Quiz Online] SET  MULTI_USER 
GO
ALTER DATABASE [Quiz Online] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Quiz Online] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Quiz Online] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Quiz Online] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Quiz Online] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Quiz Online] SET QUERY_STORE = OFF
GO
USE [Quiz Online]
GO
/****** Object:  Table [dbo].[Answer]    Script Date: 3/19/2021 7:13:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Answer](
	[answer_content] [varchar](100) NULL,
	[isCorrect] [bit] NULL,
	[questionId] [varchar](50) NOT NULL,
	[answerId] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Answer] PRIMARY KEY CLUSTERED 
(
	[questionId] ASC,
	[answerId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Question]    Script Date: 3/19/2021 7:13:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Question](
	[questionId] [varchar](50) NOT NULL,
	[subjectId] [varchar](50) NULL,
	[question_content] [varchar](max) NULL,
	[status] [varchar](50) NULL,
 CONSTRAINT [PK_Question] PRIMARY KEY CLUSTERED 
(
	[questionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Quiz]    Script Date: 3/19/2021 7:13:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Quiz](
	[quizId] [varchar](50) NOT NULL,
	[createDate] [datetime] NULL,
	[subjectId] [varchar](50) NULL,
 CONSTRAINT [PK_Quiz] PRIMARY KEY CLUSTERED 
(
	[quizId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QuizDetail]    Script Date: 3/19/2021 7:13:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QuizDetail](
	[quizDetailId] [varchar](50) NOT NULL,
	[quizId] [varchar](50) NULL,
	[questionContent] [varchar](max) NULL,
	[answerContent] [varchar](max) NULL,
	[isCorrect] [bit] NULL,
 CONSTRAINT [PK_QuizDetailId_1] PRIMARY KEY CLUSTERED 
(
	[quizDetailId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Subject]    Script Date: 3/19/2021 7:13:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subject](
	[subjectId] [varchar](50) NOT NULL,
	[name] [varchar](50) NULL,
	[numberOfQuestion] [int] NULL,
	[timeDuration] [time](7) NULL,
	[startDate] [date] NULL,
	[endDate] [date] NULL,
 CONSTRAINT [PK_Subject] PRIMARY KEY CLUSTERED 
(
	[subjectId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 3/19/2021 7:13:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[email] [varchar](50) NOT NULL,
	[name] [varchar](50) NOT NULL,
	[password] [varchar](100) NULL,
	[role] [bit] NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserQuiz]    Script Date: 3/19/2021 7:13:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserQuiz](
	[userQuizId] [varchar](50) NOT NULL,
	[totalPoint] [float] NULL,
	[timeDuration] [time](7) NULL,
	[email] [varchar](50) NULL,
	[subjectName] [varchar](50) NULL,
 CONSTRAINT [PK_UserQuizDetailId] PRIMARY KEY CLUSTERED 
(
	[userQuizId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserQuizDetail]    Script Date: 3/19/2021 7:13:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserQuizDetail](
	[userQuizDetailId] [varchar](50) NOT NULL,
	[userAnswerContent] [varchar](50) NULL,
	[quizDetailId] [varchar](50) NULL,
	[userQuizId] [varchar](50) NULL,
 CONSTRAINT [PK_QuestionDetail_1] PRIMARY KEY CLUSTERED 
(
	[userQuizDetailId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Answer]  WITH CHECK ADD  CONSTRAINT [FK_Answer_Question] FOREIGN KEY([questionId])
REFERENCES [dbo].[Question] ([questionId])
GO
ALTER TABLE [dbo].[Answer] CHECK CONSTRAINT [FK_Answer_Question]
GO
ALTER TABLE [dbo].[Question]  WITH CHECK ADD  CONSTRAINT [FK_Question_Subject] FOREIGN KEY([subjectId])
REFERENCES [dbo].[Subject] ([subjectId])
GO
ALTER TABLE [dbo].[Question] CHECK CONSTRAINT [FK_Question_Subject]
GO
ALTER TABLE [dbo].[Quiz]  WITH CHECK ADD  CONSTRAINT [FK_Quiz_Subject] FOREIGN KEY([subjectId])
REFERENCES [dbo].[Subject] ([subjectId])
GO
ALTER TABLE [dbo].[Quiz] CHECK CONSTRAINT [FK_Quiz_Subject]
GO
ALTER TABLE [dbo].[QuizDetail]  WITH CHECK ADD  CONSTRAINT [FK_QuizDetailId_Quiz] FOREIGN KEY([quizId])
REFERENCES [dbo].[Quiz] ([quizId])
GO
ALTER TABLE [dbo].[QuizDetail] CHECK CONSTRAINT [FK_QuizDetailId_Quiz]
GO
ALTER TABLE [dbo].[UserQuiz]  WITH CHECK ADD  CONSTRAINT [FK_UserQuiz_User] FOREIGN KEY([email])
REFERENCES [dbo].[User] ([email])
GO
ALTER TABLE [dbo].[UserQuiz] CHECK CONSTRAINT [FK_UserQuiz_User]
GO
ALTER TABLE [dbo].[UserQuizDetail]  WITH CHECK ADD  CONSTRAINT [FK_UserQuizDetail_QuizDetail] FOREIGN KEY([quizDetailId])
REFERENCES [dbo].[QuizDetail] ([quizDetailId])
GO
ALTER TABLE [dbo].[UserQuizDetail] CHECK CONSTRAINT [FK_UserQuizDetail_QuizDetail]
GO
ALTER TABLE [dbo].[UserQuizDetail]  WITH CHECK ADD  CONSTRAINT [FK_UserQuizDetail_UserQuiz] FOREIGN KEY([userQuizId])
REFERENCES [dbo].[UserQuiz] ([userQuizId])
GO
ALTER TABLE [dbo].[UserQuizDetail] CHECK CONSTRAINT [FK_UserQuizDetail_UserQuiz]
GO
USE [master]
GO
ALTER DATABASE [Quiz Online] SET  READ_WRITE 
GO
